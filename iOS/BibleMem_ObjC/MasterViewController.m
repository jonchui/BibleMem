//
//  MasterViewController.m
//  BibleMem_ObjC
//
//  Created by Ed Barker on 11/13/15.
//  Copyright © 2015 Ed Barker. All rights reserved.
//

#import "MasterViewController.h"
#import "DetailViewController.h"

#import <AVFoundation/AVFoundation.h>

#import <dbt-sdk/dbt.h>
#import <dbt-sdk/DBTMediaLocation.h>
#import <dbt-sdk/DBTAudioPath.h>
#import <dbt-sdk/DBTAudioVerseStart.h>

@interface MasterViewController ()

@property (nonatomic, strong) AVPlayer *audioPlayer;
@property (nonatomic, strong) IBOutlet UITableView *versesTableView;
@property (nonatomic, strong) IBOutlet UIPickerView *booksPicker;
@property (nonatomic, strong) IBOutlet UIPickerView *chapterPicker;

// should sync with picker, or be alternate input
@property (nonatomic, strong) IBOutlet UITextField *verseField;

@property (nonatomic, strong) IBOutlet UIButton *listenToVerseButton;

// Data
@property (nonatomic, strong) NSArray *volumes; //
@property (nonatomic, strong) NSArray *books; // DBTBook

// TODO: in future allow users to chose this? currntly set to @"ENGESVN2ET" in #viewDidLoad
@property (nonatomic, strong) NSString *damId;

- (IBAction)listenToVerseButtonPressed;

// Verse playback iVars
@property(nonatomic,strong) NSArray *audioVerseStartsHolder;

@property (nonatomic,strong) NSString* book;
@property (nonatomic,strong) NSNumber* chapter;
@property float playDuration;
@property int startingVerse;
@property int endingVerse;

- (void)startTimedPlayback;

@end

@implementation MasterViewController

int _numChaptersInSelectedBook = 0; // Always starts off as 0 until we hear back from server
int _numVersesInSelectedChapter = 0; // Always starts off as 0 until we hear back from server

static const NSString *DOWNLOADED_VOLUMES_FILENAME = @"downloadedVolumesAndBooks.plist";

#pragma mark
#pragma Setters and getters

- (void) setVolumes:(NSArray *)volumes {
  if (![volumes isEqual:_volumes]) {
    _volumes = volumes;
  }
  // Update picker
  [self updatePicker];
  
}

#pragma mark - System View methods

- (void)viewDidLoad {
  [super viewDidLoad];
  // TODO: in future let users chose this
  self.damId = @"ENGESVN2DA";
  
  // update picker delegate & data sources
  _chapterPicker.delegate = self;
  _booksPicker.delegate = self;
  _chapterPicker.dataSource = self;
  _booksPicker.dataSource = self;
  [_verseField addTarget:self
                action:@selector(textFieldDidChange:)
      forControlEvents:UIControlEventEditingChanged];
  
  // setup tableview
  _versesTableView.delegate = self;
  _versesTableView.dataSource = self;
  
  // Do any additional setup after loading the view, typically from a nib.
  self.navigationItem.leftBarButtonItem = self.editButtonItem;

  UIBarButtonItem *addButton = [[UIBarButtonItem alloc] initWithBarButtonSystemItem:UIBarButtonSystemItemPause target:self action:@selector(pausePlayback:)];
  self.navigationItem.rightBarButtonItem = addButton;
  self.detailViewController = (DetailViewController *)[[self.splitViewController.viewControllers lastObject] topViewController];
  
  // Download all books and populate as needed
  [DBT getLibraryBookWithDamId:self.damId
                       success:^(NSArray *books) {
                         self.books = books;
                         [self updatePicker];
                         NSLog(@"Books: %@", books);
                       } failure:^(NSError *error) {
                         NSLog(@"Error: %@", error);
                       }];
}

- (void)startTimedPlayback {
  // ERROR CONDITIONS HERE
  int zeroIndexedVerse = _startingVerse - 1;
  if (zeroIndexedVerse > [_audioVerseStartsHolder count] - 1) {
    zeroIndexedVerse = [_audioVerseStartsHolder count] - 1;
  }
  if (_endingVerse > [_audioVerseStartsHolder count] - 1) {
    _endingVerse = [_audioVerseStartsHolder count] - 1;
  }
  // TODO - mixed up?!?! Update the text view
  if (zeroIndexedVerse == _endingVerse) {
    // Opps, looks like the user chose a bad verse. let's just go to the first verse.
    [self.verseField setText:@"1"];
    zeroIndexedVerse = 0;
  } else {
    [self.verseField setText:[NSString stringWithFormat:@"%d-%d", _startingVerse, _endingVerse]];
  }
  // PLAY AS NORMAL
  float startTimeSeconds = [((DBTAudioVerseStart *)_audioVerseStartsHolder[zeroIndexedVerse]).verseStart floatValue];
  CMTime seekTargetTime = CMTimeMakeWithSeconds(startTimeSeconds, NSEC_PER_SEC);
  [self.audioPlayer seekToTime:seekTargetTime
               toleranceBefore:kCMTimePositiveInfinity toleranceAfter:kCMTimeZero];

  // For example... sample math for verses 6-8 (endTimeOffset - startTimeOffset: 39.861 - 26.344 = 13.517000)
  // @todo last verse in chapter needs special handling...

  NSAssert(_audioVerseStartsHolder != nil, @"this should not be nil");
  
  NSNumber* startTimeOffset = ((DBTAudioVerseStart *)_audioVerseStartsHolder[zeroIndexedVerse]).verseStart;
  NSNumber* endTimeOffset = ((DBTAudioVerseStart *)_audioVerseStartsHolder[_endingVerse]).verseStart;

  _playDuration = [endTimeOffset floatValue] - [startTimeOffset floatValue];
  NSLog(@"endTimeOffset - startTimeOffset: %f", _playDuration);

  verseTimer = [NSTimer scheduledTimerWithTimeInterval:_playDuration
                                                target:self
                                              selector:@selector(timerFired:)
                                              userInfo:nil
                                               repeats:NO];

  [self.audioPlayer play];
}

- (void)saveVolumesAndBooksForEnglishToFile {
  NSMutableDictionary *dict = [[NSMutableDictionary alloc] initWithCapacity:2];
  // See all possible versions
  [DBT getLibraryVolumeWithDamID:nil
                    languageCode:@"ENG"
                           media:@"text"
                         success:^(NSArray *volumes) {
                           NSLog(@"Volumes %@", volumes);
                           self.volumes = volumes;
                           [dict setObject:volumes forKey:@"volumes"];
                           // Download all books and populate as needed
                           [DBT getLibraryBookWithDamId:self.damId
                                                success:^(NSArray *books) {
                                                  self.books = books;
                                                  NSLog(@"Books: %@", books);
                                                  [dict setObject:books forKey:@"books"];
                                                  NSLog(@"Saving dict : %@, ", dict);
                                                  [dict writeToFile:DOWNLOADED_VOLUMES_FILENAME atomically:YES];
                                                } failure:^(NSError *error) {
                                                  NSLog(@"Error: %@", error);
                                                }];
                         } failure:^(NSError *error) {
                           NSLog(@"Error: %@", error);
                         }];
  
  
}

- (void)viewWillAppear:(BOOL)animated {
  self.clearsSelectionOnViewWillAppear = self.splitViewController.isCollapsed;
  [super viewWillAppear:animated];
}

#pragma mark - Update UI Methods

- (void)updatePicker {
  [self.booksPicker reloadAllComponents];
  [self.chapterPicker reloadAllComponents];
  
  [_verseField setText:@"1"];

  [self updateReadButtonWithSelectedText];
  [self.listenToVerseButton setEnabled:[self versesValid]];
}

- (void)pickerView:(UIPickerView *)pickerView didSelectRow:(NSInteger)row inComponent:(NSInteger)component {
  if (pickerView == self.booksPicker) {
    [_chapterPicker reloadAllComponents];
  } else if (pickerView == self.chapterPicker) {
    // TODO: make this better,!? why do we change the verses
    [_verseField setText:@"1"];
  }
  [self updateReadButtonWithSelectedText];
}


- (NSString *) getVerseAsString {
  return [NSString stringWithFormat:@"%@ %@:%@",
          [self selectedBook].bookName,
          [self getSelectedChapterString],
          self.verseField.text];
}
- (void) updateReadButtonWithSelectedText {
  [self.listenToVerseButton setEnabled:[self versesValid]];

  [self.listenToVerseButton setTitle:[NSString stringWithFormat:@"Memorize %@", [self getVerseAsString]]
                            forState:UIControlStateNormal];
}

- (IBAction)listenToVerseButtonPressed {
  [self setGlobalVariablesHack];
  [self insertNewObject:self];
  [self playGlobalVariablesHack];
}

- (void)textFieldDidChange :(UITextField *)textField{
  NSLog( @"text changed: %@", textField.text);
  [self updateReadButtonWithSelectedText];
}

- (nullable NSString *)pickerView:(UIPickerView *)pickerView titleForRow:(NSInteger)row forComponent:(NSInteger)component {
  if (pickerView == self.booksPicker) {
    return ((DBTBook *)self.books[row]).bookName;
  } else if (pickerView == self.chapterPicker) {
    return [NSString stringWithFormat:@"%ld", row + 1];
  }
  NSAssert(false, @"we should not be here");
  return nil;
}

// returns the number of 'columns' to display.
- (NSInteger)numberOfComponentsInPickerView:(UIPickerView *)pickerView {
  return 1;
}

// returns the # of rows in each component..
- (NSInteger)pickerView:(UIPickerView *)pickerView numberOfRowsInComponent:(NSInteger)component {
  if (pickerView == self.booksPicker) {
    return self.books.count;
  } else if (pickerView == self.chapterPicker) {
    return [self selectedBook] ? [[self selectedBook].numberOfChapters intValue] : 0;
  }

  NSAssert(false, @"we should not be here");
  return 0;
}

// returns the selected chapter, can be null if no book is selected, or no books are available.
- (__nullable DBTBook*) selectedBook {
  NSInteger selectedRow = [self.booksPicker selectedRowInComponent:0];
  return selectedRow == -1 ? nil : self.books[selectedRow];
}

- (NSInteger) getSelectedChapterInt {
  return [self.chapterPicker selectedRowInComponent:0] + 1;
}
- (NSString *) getSelectedChapterString {
return [NSString stringWithFormat:@"%d", [self getSelectedChapterInt]] ;
}

// TODO - do better validation, like verses, etc.
- (BOOL)versesValid {
  if (![self selectedBook] || !self.books || self.books.count == 0 ) {
    return false;
  }
  return true;
}

#pragma mark - play pause 

- (void)pausePlayback:(id)sender {
  [verseTimer invalidate];
  verseTimer = nil;
  [_audioPlayer pause];
}

- (void) setGlobalVariablesHack {
  // TODO fix this - and don't make these global variables.
  _book = [self selectedBook].bookId;
  _chapter = [NSNumber numberWithInt:[self getSelectedChapterInt]];
  [_verseField resignFirstResponder];
  NSArray *ints = [self.verseField.text componentsSeparatedByString: @"-"];
  if ([ints count] < 2) {
    _startingVerse = [self.verseField.text integerValue];
    _endingVerse = _startingVerse;
  } else {
    _startingVerse = [((NSString *)ints[0]) integerValue];
    _endingVerse = [((NSString *)ints[1]) integerValue];
  }
}

- (void) playGlobalVariablesHack {
  // let's do validation here, before we save to core data and crash
  if (![self versesValid]) {
    // Opps, we cannot do anything, so just quite
    return;
  }
  
  [DBT getAudioVerseStartWithDamId:self.damId
                              book:_book
                           chapter:_chapter
                           success:^(NSArray *audioVerseStarts) {
                             self.audioVerseStartsHolder = [audioVerseStarts copy];
                             [self playAudioAfterAudioStartsHolderIsSet];
                             NSLog(@"getAudioVerseStartWithDamId result: %@", audioVerseStarts );
                           }
                           failure:^(NSError *error) {
                             NSLog(@"getAudioVerseStartWithDamId failure: %@", error);
                           }
   ];
}

#pragma mark - Core Data

- (void)insertNewObject:(id)sender {
  // let's do validation here, before we save to core data and crash
  if (![self versesValid]) {
    // Opps, we cannot do anything, so just quite
    return;
  }
  
  NSManagedObjectContext *context = [self.fetchedResultsController managedObjectContext];
  NSEntityDescription *entity = [[self.fetchedResultsController fetchRequest] entity];
  NSManagedObject *newManagedObject = [NSEntityDescription insertNewObjectForEntityForName:[entity name] inManagedObjectContext:context];
  
  // If appropriate, configure the new managed object.
  // Normally you should use accessor methods, but using KVC here avoids the need to add a custom class to the template.
  [newManagedObject setValue:[NSDate date] forKey:@"timeStamp"];
  [newManagedObject setValue:_book forKey:@"bookId"];
  [newManagedObject setValue:_chapter forKey:@"chapter"];
  [newManagedObject setValue:[NSNumber numberWithInteger:_startingVerse] forKey:@"startingVerse"];
  [newManagedObject setValue:[NSNumber numberWithInteger:_endingVerse] forKey:@"endingVerse"];
  [newManagedObject setValue:[self getVerseAsString] forKey:@"displayString"];
  
  // Save the context.
  NSError *error = nil;
  if (![context save:&error]) {
      // Replace this implementation with code to handle the error appropriately.
      // abort() causes the application to generate a crash log and terminate. You should not use this function in a shipping application, although it may be useful during development.
      NSLog(@"Unresolved error %@, %@", error, [error userInfo]);
      abort();
  }
}

- (void)playAudioAfterAudioStartsHolderIsSet {
  [DBT getAudioLocation:@"http"
                success:^(NSArray *audioLocations) {
                  if(audioLocations.count) {
                    DBTMediaLocation *location = audioLocations[0];
                    [DBT getAudioPathWithDamId:self.damId
                                          book:_book
                                       chapter:_chapter
                                       success:^(NSArray *audioPaths) {
                                         NSLog(@"paths %@", audioPaths);
                                         if(audioPaths.count) {
                                           DBTAudioPath *audioPath = audioPaths[0];
                                           NSString *urlString = [NSString stringWithFormat:@"%@/%@",location.baseURL,audioPath.path];
                                           NSURL *url = [NSURL URLWithString:urlString];
                                           NSLog(@"Audio file URL: %@", url);
                                           
                                           [self cleanupOldAudioPlayer];
                                           AVPlayer *player = [[AVPlayer alloc]initWithURL:url];
                                           _audioPlayer = player;
                                           [_audioPlayer addObserver:self forKeyPath:@"status" options:0 context:nil];
                                           
                                           NSLog(@"After play: %@", urlString);
                                           
                                         }
                                       } failure:^(NSError *error) {
                                         [_audioPlayer pause];
                                         [self cleanupOldAudioPlayer];
                                         NSLog(@"Audio Path Error: %@", error);
                                       }];
                  }
                } failure:^(NSError *error) {
                  NSLog(@"Audio Location Error: %@", error);
                  [_audioPlayer pause];
                  [self cleanupOldAudioPlayer];
                }];

}

// CLEANUP audioplaye before we nil it out, is there way around this?
- (void)cleanupOldAudioPlayer {
  [_audioPlayer pause];
  [_audioPlayer removeObserver:self forKeyPath:@"status"];
  _audioPlayer = nil;
}

- (void)observeValueForKeyPath:(NSString *)keyPath ofObject:(id)object change:(NSDictionary *)change context:(void *)context
{

  if (object == self.audioPlayer && [keyPath isEqualToString:@"status"]) {
    if (self.audioPlayer.status == AVPlayerStatusFailed)
    {
      NSLog(@"AVPlayer Failed");
    }
    else if (self.audioPlayer.status == AVPlayerStatusReadyToPlay)
    {
      [self startTimedPlayback];

    }
    else if (self.audioPlayer.status == AVPlayerItemStatusUnknown)
    {
       NSLog(@"AVPlayer unknown status");
    }
  }
}

#pragma mark - Memory

- (void)didReceiveMemoryWarning {
  [super didReceiveMemoryWarning];
  // Dispose of any resources that can be recreated.
}


#pragma mark - Segues

- (void)prepareForSegue:(UIStoryboardSegue *)segue sender:(id)sender {
  if ([[segue identifier] isEqualToString:@"showDetail"]) {
      NSIndexPath *indexPath = [self.tableView indexPathForSelectedRow];
      NSManagedObject *object = [[self fetchedResultsController] objectAtIndexPath:indexPath];
      DetailViewController *controller = (DetailViewController *)[[segue destinationViewController] topViewController];
      [controller setDetailItem:object];
      controller.navigationItem.leftBarButtonItem = self.splitViewController.displayModeButtonItem;
      controller.navigationItem.leftItemsSupplementBackButton = YES;
  }
}

#pragma mark - Table View

- (NSInteger)numberOfSectionsInTableView:(UITableView *)tableView {
  return [[self.fetchedResultsController sections] count];
}

- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section {
  id <NSFetchedResultsSectionInfo> sectionInfo = [self.fetchedResultsController sections][section];
  return [sectionInfo numberOfObjects];
}

- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath {
  UITableViewCell *cell = [tableView dequeueReusableCellWithIdentifier:@"Cell" forIndexPath:indexPath];
  [self configureCell:cell atIndexPath:indexPath];
  return cell;
}

- (BOOL)tableView:(UITableView *)tableView canEditRowAtIndexPath:(NSIndexPath *)indexPath {
  // Return NO if you do not want the specified item to be editable.
  return YES;
}

- (void)tableView:(UITableView *)tableView commitEditingStyle:(UITableViewCellEditingStyle)editingStyle forRowAtIndexPath:(NSIndexPath *)indexPath {
  if (editingStyle == UITableViewCellEditingStyleDelete) {
      NSManagedObjectContext *context = [self.fetchedResultsController managedObjectContext];
      [context deleteObject:[self.fetchedResultsController objectAtIndexPath:indexPath]];
          
      NSError *error = nil;
      if (![context save:&error]) {
          // Replace this implementation with code to handle the error appropriately.
          // abort() causes the application to generate a crash log and terminate. You should not use this function in a shipping application, although it may be useful during development.
          NSLog(@"Unresolved error %@, %@", error, [error userInfo]);
          abort();
      }
  }
}

- (void)configureCell:(UITableViewCell *)cell atIndexPath:(NSIndexPath *)indexPath {
  NSManagedObject *object = [self.fetchedResultsController objectAtIndexPath:indexPath];
  cell.textLabel.text = [[object valueForKey:@"displayString"] description];
}

- (void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath {
  NSManagedObject *history = [self getSelectedCoreDataObjectOrNil];
  // set global state, ugh....
  _book = [history valueForKey:@"bookId"];
  _chapter = [history valueForKey:@"chapter"];
  _startingVerse = [[history valueForKey:@"startingVerse"] integerValue];
  _endingVerse = [[history valueForKey:@"endingVerse"] integerValue];
  [self playGlobalVariablesHack];
}

- (NSManagedObject *)getSelectedCoreDataObjectOrNil {
  NSIndexPath *indexPath = [self.tableView indexPathForSelectedRow];
  if (!indexPath) {
    return nil;
  }
  return [[self fetchedResultsController] objectAtIndexPath:indexPath];
}

#pragma mark - Fetched results controller

- (NSFetchedResultsController *)fetchedResultsController
{
    if (_fetchedResultsController != nil) {
        return _fetchedResultsController;
    }
    
    NSFetchRequest *fetchRequest = [[NSFetchRequest alloc] init];
    // Edit the entity name as appropriate.
    NSEntityDescription *entity = [NSEntityDescription entityForName:@"Event" inManagedObjectContext:self.managedObjectContext];
    [fetchRequest setEntity:entity];
    
    // Set the batch size to a suitable number.
    [fetchRequest setFetchBatchSize:20];
    
    // Edit the sort key as appropriate.
    NSSortDescriptor *sortDescriptor = [[NSSortDescriptor alloc] initWithKey:@"timeStamp" ascending:NO];

    [fetchRequest setSortDescriptors:@[sortDescriptor]];
    
    // Edit the section name key path and cache name if appropriate.
    // nil for section name key path means "no sections".
    NSFetchedResultsController *aFetchedResultsController = [[NSFetchedResultsController alloc] initWithFetchRequest:fetchRequest managedObjectContext:self.managedObjectContext sectionNameKeyPath:nil cacheName:@"Master"];
    aFetchedResultsController.delegate = self;
    self.fetchedResultsController = aFetchedResultsController;
    
	NSError *error = nil;
	if (![self.fetchedResultsController performFetch:&error]) {
	     // Replace this implementation with code to handle the error appropriately.
	     // abort() causes the application to generate a crash log and terminate. You should not use this function in a shipping application, although it may be useful during development. 
	    NSLog(@"Unresolved error %@, %@", error, [error userInfo]);
	    abort();
	}
    
    return _fetchedResultsController;
}    

- (void)controllerWillChangeContent:(NSFetchedResultsController *)controller
{
    [self.tableView beginUpdates];
}

- (void)controller:(NSFetchedResultsController *)controller didChangeSection:(id <NSFetchedResultsSectionInfo>)sectionInfo
           atIndex:(NSUInteger)sectionIndex forChangeType:(NSFetchedResultsChangeType)type
{
    switch(type) {
        case NSFetchedResultsChangeInsert:
            [self.tableView insertSections:[NSIndexSet indexSetWithIndex:sectionIndex] withRowAnimation:UITableViewRowAnimationFade];
            break;
            
        case NSFetchedResultsChangeDelete:
            [self.tableView deleteSections:[NSIndexSet indexSetWithIndex:sectionIndex] withRowAnimation:UITableViewRowAnimationFade];
            break;
            
        default:
            return;
    }
}

- (void)controller:(NSFetchedResultsController *)controller didChangeObject:(id)anObject
       atIndexPath:(NSIndexPath *)indexPath forChangeType:(NSFetchedResultsChangeType)type
      newIndexPath:(NSIndexPath *)newIndexPath
{
    UITableView *tableView = self.tableView;
    
    switch(type) {
        case NSFetchedResultsChangeInsert:
            [tableView insertRowsAtIndexPaths:@[newIndexPath] withRowAnimation:UITableViewRowAnimationFade];
            break;
            
        case NSFetchedResultsChangeDelete:
            [tableView deleteRowsAtIndexPaths:@[indexPath] withRowAnimation:UITableViewRowAnimationFade];
            break;
            
        case NSFetchedResultsChangeUpdate:
            [self configureCell:[tableView cellForRowAtIndexPath:indexPath] atIndexPath:indexPath];
            break;
            
        case NSFetchedResultsChangeMove:
            [tableView deleteRowsAtIndexPaths:@[indexPath] withRowAnimation:UITableViewRowAnimationFade];
            [tableView insertRowsAtIndexPaths:@[newIndexPath] withRowAnimation:UITableViewRowAnimationFade];
            break;
    }
}

- (void)controllerDidChangeContent:(NSFetchedResultsController *)controller
{
    [self.tableView endUpdates];
}

-(void)timerFired:(NSTimer *) theTimer
{
  if (theTimer == verseTimer) {
    NSLog(@"timerFired !!! @ %@", [theTimer fireDate]);
    [self.audioPlayer pause];

    [self startTimedPlayback];
  }
}

/*
// Implementing the above methods to update the table view in response to individual changes may have performance implications if a large number of changes are made simultaneously. If this proves to be an issue, you can instead just implement controllerDidChangeContent: which notifies the delegate that all section and object changes have been processed. 
 
 - (void)controllerDidChangeContent:(NSFetchedResultsController *)controller
{
    // In the simplest, most efficient, case, reload the table view.
    [self.tableView reloadData];
}
 */

@end
