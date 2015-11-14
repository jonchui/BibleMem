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

@interface MasterViewController ()

@property (nonatomic,strong)    AVPlayer *audioPlayer;

@end

@implementation MasterViewController

- (void)viewDidLoad {
  [super viewDidLoad];
  // Do any additional setup after loading the view, typically from a nib.
  self.navigationItem.leftBarButtonItem = self.editButtonItem;

  UIBarButtonItem *addButton = [[UIBarButtonItem alloc] initWithBarButtonSystemItem:UIBarButtonSystemItemAdd target:self action:@selector(insertNewObject:)];
  self.navigationItem.rightBarButtonItem = addButton;
  self.detailViewController = (DetailViewController *)[[self.splitViewController.viewControllers lastObject] topViewController];
}

- (void)viewWillAppear:(BOOL)animated {
  self.clearsSelectionOnViewWillAppear = self.splitViewController.isCollapsed;
  [super viewWillAppear:animated];
}

- (void)didReceiveMemoryWarning {
  [super didReceiveMemoryWarning];
  // Dispose of any resources that can be recreated.
}

- (void)insertNewObject:(id)sender {
  NSManagedObjectContext *context = [self.fetchedResultsController managedObjectContext];
  NSEntityDescription *entity = [[self.fetchedResultsController fetchRequest] entity];
  NSManagedObject *newManagedObject = [NSEntityDescription insertNewObjectForEntityForName:[entity name] inManagedObjectContext:context];
      
  // If appropriate, configure the new managed object.
  // Normally you should use accessor methods, but using KVC here avoids the need to add a custom class to the template.
  [newManagedObject setValue:[NSDate date] forKey:@"timeStamp"];

  [newManagedObject setValue:[NSDate date] forKey:@"timeStamp"];


  // Save the context.
  NSError *error = nil;
  if (![context save:&error]) {
      // Replace this implementation with code to handle the error appropriately.
      // abort() causes the application to generate a crash log and terminate. You should not use this function in a shipping application, although it may be useful during development.
      NSLog(@"Unresolved error %@, %@", error, [error userInfo]);
      abort();
  }
  
  [DBT getTextVerseWithDamId:@"ENGESVN2ET"
                        book:@"John"
                     chapter:@3
                  verseStart:@1
                    verseEnd:@1
                     success:^(NSArray *verses) {
//                       NSLog(@"Verse: %@", );
                       AVSpeechSynthesizer *synthesizer = [[AVSpeechSynthesizer alloc]init];
                       AVSpeechUtterance *utterance = [AVSpeechUtterance speechUtteranceWithString:((DBTVerse *)[verses objectAtIndex:0]).verseText];
                       [utterance setRate:0.5f];
                       [utterance setPitchMultiplier:2];
//                       [synthesizer speakUtterance:utterance];
                     } failure:^(NSError *error) {
                       NSLog(@"Error: %@", error);
                     }];

  [DBT getAudioVerseStartWithDamId:@"ENGESVN2DA"
                              book:@"John"
                           chapter:@1
                           success:^(NSArray *audioVerseStarts) {
                             NSLog(@"getAudioVerseStartWithDamId result: %@", audioVerseStarts );
                           }
   failure:^(NSError *error) {
     NSLog(@"getAudioVerseStartWithDamId failure: %@", error);
   }
   ];


  [DBT getAudioLocation:@"http"
                success:^(NSArray *audioLocations) {
                  if(audioLocations.count) {
                    DBTMediaLocation *location = audioLocations[0];
                    [DBT getAudioPathWithDamId:@"ENGESVN2DA"
                                          book:@"John"
                                       chapter:@1
                                       success:^(NSArray *audioPaths) {
                                         NSLog(@"paths %@", audioPaths);
                                         if(audioPaths.count) {
                                           DBTAudioPath *audioPath = audioPaths[0];
                                           NSString *urlString = [NSString stringWithFormat:@"%@/%@",location.baseURL,audioPath.path];
                                           NSURL *url = [NSURL URLWithString:urlString];
                                           NSLog(@"Audio file URL: %@", url);

                                           AVPlayer *player = [[AVPlayer alloc]initWithURL:url];
                                           _audioPlayer= player;
                                           [_audioPlayer addObserver:self forKeyPath:@"status" options:0 context:nil];


                                           NSLog(@"After play: %@", urlString);

                                         }
                                       } failure:^(NSError *error) {
                                         NSLog(@"Audio Path Error: %@", error);
                                       }];
                  }
                } failure:^(NSError *error) {
                  NSLog(@"Audio Location Error: %@", error);
                }];


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
      Float64 seconds = 78.132f;  // Verse 15 start
      CMTime targetTime = CMTimeMakeWithSeconds(seconds, NSEC_PER_SEC);
      [self.audioPlayer seekToTime:targetTime
              toleranceBefore:kCMTimePositiveInfinity toleranceAfter:kCMTimeZero];

      verseTimer = [NSTimer scheduledTimerWithTimeInterval:11.424  // Verse 15 duration: 89.556 - 78.132
                                                target:self
                                              selector:@selector(timerFired:)
                                              userInfo:nil
                                               repeats:NO];

      [self.audioPlayer play];

    }
    else if (self.audioPlayer.status == AVPlayerItemStatusUnknown)
    {
       NSLog(@"AVPlayer unknown status");
    }
  }
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
  cell.textLabel.text = [[object valueForKey:@"timeStamp"] description];
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
