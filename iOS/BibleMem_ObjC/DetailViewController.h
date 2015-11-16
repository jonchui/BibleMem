//
//  DetailViewController.h
//  BibleMem_ObjC
//
//  Created by Ed Barker on 11/13/15.
//  Copyright © 2015 Ed Barker. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "NSManagedObjects/Event.h"

@interface DetailViewController : UITableViewController

@property (strong, nonatomic) Event *event;
@property (weak, nonatomic) IBOutlet UILabel *detailDescriptionLabel;

- (id)initWithEvent:(Event *)event;

@end

