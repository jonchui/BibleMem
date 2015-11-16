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

- (void)viewDidLoad {
  [super viewDidLoad];
  // Do any additional setup after loading the view, typically from a nib.
  [self configureView];
}

- (void)didReceiveMemoryWarning {
  [super didReceiveMemoryWarning];
  // Dispose of any resources that can be recreated.
}

@end
