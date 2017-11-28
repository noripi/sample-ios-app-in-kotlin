//
//  EditViewController.h
//  todo-list
//
//  Created by NoriyukiIshida on 2017/11/27.
//  Copyright © 2017年 Noriyuki Ishida. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface EditViewController : UIViewController
@property (weak, nonatomic) IBOutlet UITextField *taskTitleTextField;
@property (weak, nonatomic) IBOutlet UILabel *taskDeadlineLabel;

- (IBAction)deadlineUpdateButtonDidTap:(id)sender;
@end
