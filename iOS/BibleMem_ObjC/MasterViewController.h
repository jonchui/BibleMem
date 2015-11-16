//
//  MasterViewController.h
//  BibleMem_ObjC
//
//  Created by Ed Barker on 11/13/15.
//  Copyright © 2015 Ed Barker. All rights reserved.
//

#import <UIKit/UIKit.h>
#import <CoreData/CoreData.h>
#import <AVFoundation/AVFoundation.h>

#import "DetailViewController.h"
//@class DetailViewController;

@interface MasterViewController : UITableViewController <
    NSFetchedResultsControllerDelegate,
    UIPickerViewDelegate,
    UIPickerViewDataSource,
    VerseAudioPlayerDelegate> {
  NSTimer *verseTimer;
}

@property (strong, nonatomic) DetailViewController *detailViewController;

@property (strong, nonatomic) NSFetchedResultsController *fetchedResultsController;
@property (strong, nonatomic) NSManagedObjectContext *managedObjectContext;

@end

