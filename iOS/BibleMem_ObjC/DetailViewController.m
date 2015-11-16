//
//  DetailViewController.m
//  BibleMem_ObjC
//
//  Created by Ed Barker on 11/13/15.
//  Copyright © 2015 Ed Barker. All rights reserved.
//

#import "DetailViewController.h"
#import <dbt-sdk/dbt.h>
#import "VerseTableViewCell.h"
#import <AVFoundation/AVFoundation.h>

@interface DetailViewController ()

@property NSArray *verses;

@property (nonatomic,strong) UIBarButtonItem *playButton;
@property (nonatomic,strong) UIBarButtonItem *pauseButton;

@end

@implementation DetailViewController

- (id)initWithEvent:(Event *)event {
  [self setEvent:event];
   return [super init];
}

- (void)updatePlayButtonState {
  if ([_verseAudioPlayerDelegate isPlaying]) {
    self.navigationItem.rightBarButtonItem = _pauseButton;
  } else {
    self.navigationItem.rightBarButtonItem = _playButton;
  }
}

#pragma mark - Managing the detail item

- (void)setVerseAudioPlayerDelegate:(id<VerseAudioPlayerDelegate>)verseAudioPlayerDelegate {
  if (verseAudioPlayerDelegate != _verseAudioPlayerDelegate) {
    [_verseAudioPlayerDelegate pause];
    _verseAudioPlayerDelegate = verseAudioPlayerDelegate;
  }
  [self updatePlayButtonState];
}

- (void)setEvent:(Event *)event {
  if (_event != event) {
      _event = event;
    
      // Download data
    [DBT getTextVerseWithDamId:@"ENGESVN2ET"
                          book:_event.bookId
                       chapter:_event.chapter
                    verseStart:_event.startingVerse
                      verseEnd:_event.endingVerse
                       success:^(NSArray *verses) {
                         NSLog(@"Verses: %@", verses);
                         self.verses = verses;
                         // Update the view.
                         [self configureView];
                       } failure:^(NSError *error) {
                         NSLog(@"Error: %@", error);
                       }];
  
  }
}

- (void)configureView {
  // Update the user interface for the detail item.
  [self.tableView reloadData];
}

#pragma mark - Table View

- (BOOL) hasDownloadedVerseData {
  return [self.verses count] > 0;
}
- (NSInteger)numberOfSectionsInTableView:(UITableView *)tableView {
  return 1;
}

- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section {
  return [self hasDownloadedVerseData] ? [self.verses count] : 1;
}

- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath {
  NSString *identifier = @"VerseCell";
  UITableViewCell *cell =
      [tableView dequeueReusableCellWithIdentifier:identifier forIndexPath:indexPath];
  [self configureCell:cell atIndexPath:indexPath];
  return cell;
}

- (BOOL)tableView:(UITableView *)tableView canEditRowAtIndexPath:(NSIndexPath *)indexPath {
  // Return NO if you do not want the specified item to be editable.
  return NO;
}

- (void)configureCell:(UITableViewCell *)cell atIndexPath:(NSIndexPath *)indexPath {
  
  if ([self hasDownloadedVerseData]) {
    DBTVerse *verse = (DBTVerse *)[self.verses objectAtIndex:indexPath.row];
    cell.textLabel.text = [verse.verseText stringByReplacingOccurrencesOfString:@"\n" withString:@""];
    cell.textLabel.lineBreakMode = NSLineBreakByWordWrapping;
    [cell.textLabel setNumberOfLines:0];
//    DOES NOT WORK YET
//    UITextView *textView = (UITextView *)[cell.contentView viewWithTag:100];
//    if (!textView) {
//      textView.text = verse.verseText;
//    }
  } else {
    cell.textLabel.text = @"Downloading... ";
  }
}

//- (CGFloat)tableView:(UITableView *)tableView heightForRowAtIndexPath:(NSIndexPath *)indexPath {
//  if ([self hasDownloadedVerseData]) {
//    DBTVerse *verse = (DBTVerse *)[self.verses objectAtIndex:indexPath.row];
//    NSString *verseText = verse.verseText;
//    CGFloat width = ((CGRect)[self.tableView bounds]).size.width;
//    NSAssert(width > 0, @"uh oh , width should be > 0");
//    CGSize size = [verseText sizeWithFont:[UIFont systemFontOfSize:[UIFont systemFontSize]] forWidth:width lineBreakMode:NSLineBreakByWordWrapping];
//    return size.height;
//  } else {
//    return 40.0f;
//  }
//}
- (void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath {

}

#pragma mark - system view stuff

- (void)viewDidLoad {
  [super viewDidLoad];
  
  [self.tableView registerClass:VerseTableViewCell.class forCellReuseIdentifier:@"VerseCell"];
  // Do any additional setup after loading the view, typically from a nib.
  
  self.tableView.estimatedRowHeight = 500.0;
  self.tableView.rowHeight = UITableViewAutomaticDimension;
  
  [self configureView];
  
  _pauseButton = [[UIBarButtonItem alloc] initWithBarButtonSystemItem:UIBarButtonSystemItemPause target:self action:@selector(togglePlayback:)];
  _playButton = [[UIBarButtonItem alloc] initWithBarButtonSystemItem:UIBarButtonSystemItemPlay target:self action:@selector(togglePlayback:)];
  [self updatePlayButtonState];
}

- (void)togglePlayback:(id)sender {
  if ([self.verseAudioPlayerDelegate isPlaying]) {
    [self.verseAudioPlayerDelegate pause];
  } else {
    [self.verseAudioPlayerDelegate play];
  }
  [self updatePlayButtonState];
}

#pragma mark - memory

- (void)didReceiveMemoryWarning {
  [super didReceiveMemoryWarning];
  // Dispose of any resources that can be recreated.
}

@end
