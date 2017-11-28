//
//  ListViewController.h
//  todo-list
//
//  Created by NoriyukiIshida on 2017/11/16.
//  Copyright © 2017年 Noriyuki Ishida. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface ListViewController : UIViewController
@end

@interface TaskItemTableViewCell: UITableViewCell
@property (weak, nonatomic) IBOutlet UILabel *taskTitleLabel;
@property (weak, nonatomic) IBOutlet UILabel *taskDeadlineLabel;
@end
