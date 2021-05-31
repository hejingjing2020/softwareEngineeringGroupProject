# CUHKSZer-Helper

## Table of Contents

- [Background](#background)
- [Install](#install)
- [Design](#design)
- [Contact](#contact)

## Background

The Chinese University of Hong Kong, (Shenzhen) is a rising international research- based university. Based on our research, we found some problems in the information communication in our school. The three most important problems are the curriculum evaluation system, developing friendships, and research assistant recruiting.

There are some problems with the curriculum evaluation system of our school. Firstly, the evaluation can only be conducted before the final examination. At this time, students have no idea about their final grades. However, grades of courses are the most crucial factor in course evaluation from students’ perspectives. Secondly, it is difficult for students to see the objective comments on professors and their courses. First of all, our school never publishes students' evaluation form of professors and courses. Students could only basically know the comments of courses from their friends. However, most of the students who openly evaluate professors and courses in their circle of friends do not like this course very much, which may lead to the lack of objective information gained by students.

At the same time, many students have difficulties in developing friendships in our school. Unlike the traditional school, our school doesn’t have the concept of class. As a result, students have less contact with one another. Developing friendship is more difficult in our school.

Finally, professors in our school often have the demand to recruit student research assistants or USTF. Many students also want to have a chance to work as research assistants or USTF. However, the recruitment information could only be sent through email or WeChat group now. If the professors send recruitment emails to all the students in our schools, they may be considered annoying. If the professors send recruitment information in the WeChat groups, they also have difficulties finding target students. Students are dispersed into different groups, and professors are usually not in the groups. All these problems hinder professors from recruiting research assistants or USTF and students from becoming research assistants or USTF.

## Install

This project uses [Android Studio](https://developer.android.google.cn/studio/). Go check it out if you don't have them locally installed.

> You need to install dependencies used by hint as well.

## Design

Two systems are introduced to solve problem described above. The 1st system: course evaluation is the most important object in our proposal. The 2nd system: Intern Employment System would be developed if time permits (done).


### Course Evaluation System

A course evaluation system will be introduced for students to make better decision in
course choosing.
The user can do the following operations:
(1) Search Courses
1) Enter keyword in quick search bar, related courses in the database would be shown to users
2) Select tags in the slider bar at the top of the view, we would select related courses in the database
(2) Press to enter the secondary interface showing all comment of the course
1) If user is valid student of the course, they are able to leave one comment
(cannot re-comment a course)
2) If user is valid student of the course, they can press "useful" or "useless" for
a specific comment (cannot press both)

### Intern Employment System (Done)
An intern employment system will be introduced for both students and teaching staff to achieve convenient internship job application. Teaching staffs can post internship request along with requirement. Students can search jobs by keywords and send their resumes directly in application.
For teaching staff users, they can do the following operations: 
(1) Post a recruit

(2) Enter self-center:
1)All recruit listed
2)Delete a recruit post
3)Press a recruit and look all resumes received.

For student users, they can do the following:
(1) Click apply for a recruit, upload a resume and submit
(2) Search for jobs by keywords. The system would return related recruit
(3) Select tags (RA, USTF) in the slider bar at the top of the view, we would select related



## Contact

[CUHKSZ]
Back-end programmar: He Jingjing 118010092@link.cuhk.edu.cn; Xia Minghong 118010340@link.cuhk.edu.cn

Front-end programmar: Yang Jiani 11810366@link.cuhk.edu.cn

Test programmar: Li Qingjun 118010147@link.cuhk.edu.cn
