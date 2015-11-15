//
//  Verse+CoreDataProperties.h
//  
//
//  Created by Jon Chui on 11/15/15.
//
//
//  Choose "Create NSManagedObject Subclass…" from the Core Data editor menu
//  to delete and recreate this implementation file for your updated model.
//

#import "Verse.h"

NS_ASSUME_NONNULL_BEGIN

@interface Verse (CoreDataProperties)

@property (nullable, nonatomic, retain) NSString *chapter;
@property (nullable, nonatomic, retain) NSString *book;
@property (nullable, nonatomic, retain) NSNumber *verseNumber;
@property (nullable, nonatomic, retain) NSString *text;

@end

NS_ASSUME_NONNULL_END
