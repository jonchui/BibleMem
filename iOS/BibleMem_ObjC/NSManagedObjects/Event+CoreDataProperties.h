//
//  Event+CoreDataProperties.h
//  
//
//  Created by Jon Chui on 11/15/15.
//
//
//  Choose "Create NSManagedObject Subclass…" from the Core Data editor menu
//  to delete and recreate this implementation file for your updated model.
//

#import "Event.h"

NS_ASSUME_NONNULL_BEGIN

@interface Event (CoreDataProperties)

@property (nullable, nonatomic, retain) NSString *bookId;
@property (nullable, nonatomic, retain) NSNumber *chapter;
@property (nullable, nonatomic, retain) NSString *displayString;
@property (nullable, nonatomic, retain) NSNumber *endingVerse;
@property (nullable, nonatomic, retain) NSNumber *startingVerse;
@property (nullable, nonatomic, retain) NSDate *timeStamp;
@property (nullable, nonatomic, retain) NSManagedObject *verses;

@end

NS_ASSUME_NONNULL_END
