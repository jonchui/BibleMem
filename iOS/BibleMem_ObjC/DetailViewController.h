//
//  DetailViewController.h
//  BibleMem_ObjC
//
//  Created by Ed Barker on 11/13/15.
//  Copyright © 2015 Ed Barker. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "NSManagedObjects/Event.h"

@protocol VerseAudioPlayerDelegate
@required
-(void)pause;
-(void)play;
@end

@interface DetailViewController : UITableViewController

@property (strong, nonatomic) Event *event;
@property (weak, nonatomic) IBOutlet UILabel *detailDescriptionLabel;

- (id)initWithEvent:(Event *)event;

@property(assign, nullable) id<VerseAudioPlayerDelegate> verseAudioPlayerDelegate;

@end

