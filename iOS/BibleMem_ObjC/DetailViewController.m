//
//  DetailViewController.m
//  BibleMem_ObjC
//
//  Created by Ed Barker on 11/13/15.
//  Copyright © 2015 Ed Barker. All rights reserved.
//

#import "DetailViewController.h"
#import <dbt-sdk/dbt.h>

@interface DetailViewController ()

@property NSArray *verses;

@end

@implementation DetailViewController

- (id)initWithEvent:(Event *)event {
  [self setEvent:event];
   return [super init];
}
#pragma mark - Managing the detail item

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
  NSString *identifier = [self hasDownloadedVerseData] ? @"VerseCell" : @"LoadingCell";
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
    cell.textLabel.text = verse.verseText;
    UITextView *textView = (UITextView *)[cell.contentView viewWithTag:100];
    if (!textView) {
      textView.text = verse.verseText;
    }
  } else {
    cell.textLabel.text = @"Downloading... ";
  }
}

- (void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath {

}

#pragma mark - system view stuff

- (void)viewDidLoad {
  [super viewDidLoad];
  // Do any additional setup after loading the view, typically from a nib.
  [self configureView];
}

#pragma mark - memory

- (void)didReceiveMemoryWarning {
  [super didReceiveMemoryWarning];
  // Dispose of any resources that can be recreated.
}

@end
