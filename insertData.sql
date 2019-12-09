INSERT INTO `mydb`.`role` (`name`) VALUES ('Админ');
INSERT INTO `mydb`.`role` (`name`) VALUES ('Инспектор');
INSERT INTO `mydb`.`role` (`name`) VALUES ('Юр.Лицо');
INSERT INTO `mydb`.`role` (`name`) VALUES ('Физ.Лицо');

INSERT INTO `mydb`.`report_status` (`status`) VALUES ('new');
INSERT INTO `mydb`.`report_status` (`status`) VALUES ('accepted');
INSERT INTO `mydb`.`report_status` (`status`) VALUES ('rejected');
INSERT INTO `mydb`.`report_status` (`status`) VALUES ('updated');

INSERT INTO `mydb`.`action_type` (`type`) VALUES ('view');
INSERT INTO `mydb`.`action_type` (`type`) VALUES ('accept');
INSERT INTO `mydb`.`action_type` (`type`) VALUES ('reject');
INSERT INTO `mydb`.`action_type` (`type`) VALUES ('ask for change');

INSERT INTO `mydb`.`users` (`email`, `password`, `name`, `surname`, `patronymic`, `ind_code`, `role_id`) VALUES ('user1@gmail.com', 'a722c63db8ec8625af6cf71cb8c2d939', 'Ігор', 'Сухий', 'Іванович', '1234567891', '3');
INSERT INTO `mydb`.`users` (`email`, `password`, `name`, `surname`, `patronymic`, `ind_code`, `role_id`) VALUES ('user2@gmail.com', 'a722c63db8ec8625af6cf71cb8c2d939', 'Іван', 'Чалий', 'Васильович', '1234567892', '3');
INSERT INTO `mydb`.`users` (`email`, `password`, `name`, `surname`, `patronymic`, `ind_code`, `role_id`) VALUES ('user3@gmail.com', 'a722c63db8ec8625af6cf71cb8c2d939', 'Андрій', 'Сухий', 'Андрійович', '1234567893', '3');
INSERT INTO `mydb`.`users` (`email`, `password`, `name`, `surname`, `patronymic`, `ind_code`, `role_id`) VALUES ('user4@gmail.com', 'a722c63db8ec8625af6cf71cb8c2d939', 'Назар', 'Коваленко', 'Ігорович', '1234567894', '3');
INSERT INTO `mydb`.`users` (`email`, `password`, `name`, `surname`, `patronymic`, `ind_code`, `role_id`) VALUES ('user5@gmail.com', 'a722c63db8ec8625af6cf71cb8c2d939', 'Інна', 'Шевченко', 'Вікторович', '1234567895', '3');
INSERT INTO `mydb`.`users` (`email`, `password`, `name`, `surname`, `patronymic`, `ind_code`, `role_id`) VALUES ('user6@gmail.com', 'a722c63db8ec8625af6cf71cb8c2d939', 'Ірина', 'Бондаренко', 'Анатольович', '1234567896', '3');
INSERT INTO `mydb`.`users` (`email`, `password`, `name`, `surname`, `patronymic`, `ind_code`, `role_id`) VALUES ('user7@gmail.com', 'a722c63db8ec8625af6cf71cb8c2d939', 'Анастасія', 'Ковальчук', 'Назарович', '1234567897', '3');
INSERT INTO `mydb`.`users` (`email`, `password`, `name`, `surname`, `patronymic`, `ind_code`, `role_id`) VALUES ('user8@gmail.com', 'a722c63db8ec8625af6cf71cb8c2d939', 'Віктор', 'Ткаченко', 'Ігорович', '1234567898', '3');
INSERT INTO `mydb`.`users` (`email`, `password`, `name`, `surname`, `patronymic`, `ind_code`, `role_id`) VALUES ('user9@gmail.com', 'a722c63db8ec8625af6cf71cb8c2d939', 'Микола', 'Бойко', 'Леондович', '1234567899', '3');
INSERT INTO `mydb`.`users` (`email`, `password`, `name`, `surname`, `patronymic`, `ind_code`, `role_id`) VALUES ('user10@gmail.com', 'a722c63db8ec8625af6cf71cb8c2d939', 'Володимир', 'Мельник', 'Степанович', '1234567810', '3');
INSERT INTO `mydb`.`users` (`email`, `password`, `name`, `surname`, `patronymic`, `ind_code`, `role_id`) VALUES ('user11@gmail.com', 'a722c63db8ec8625af6cf71cb8c2d939', 'Сергій', 'Кравченко', 'Миколайович', '1234567811', '3');
INSERT INTO `mydb`.`users` (`email`, `password`, `name`, `surname`, `patronymic`, `ind_code`, `role_id`) VALUES ('user12@gmail.com', 'a722c63db8ec8625af6cf71cb8c2d939', 'Анатолій', 'Олійник', 'Володимирович', '1234567812', '3');
INSERT INTO `mydb`.`users` (`email`, `password`, `name`, `surname`, `patronymic`, `ind_code`, `role_id`) VALUES ('user13@gmail.com', 'a722c63db8ec8625af6cf71cb8c2d939', 'Вікторія', 'Коваль', 'Петрович', '1234567813', '3');
INSERT INTO `mydb`.`users` (`email`, `password`, `name`, `surname`, `patronymic`, `ind_code`, `role_id`) VALUES ('user14@gmail.com', 'a722c63db8ec8625af6cf71cb8c2d939', 'Леонід', 'Павленко', 'Васильович', '1234567814', '3');
INSERT INTO `mydb`.`users` (`email`, `password`, `name`, `surname`, `patronymic`, `ind_code`, `role_id`) VALUES ('user15@gmail.com', 'a722c63db8ec8625af6cf71cb8c2d939', 'Ігор', 'Олійник', 'Петрович', '34567815', '4');
INSERT INTO `mydb`.`users` (`email`, `password`, `name`, `surname`, `patronymic`, `ind_code`, `role_id`) VALUES ('user16@gmail.com', 'a722c63db8ec8625af6cf71cb8c2d939', 'Іван', 'Павленко', 'Васильович', '34567816', '4');
INSERT INTO `mydb`.`users` (`email`, `password`, `name`, `surname`, `patronymic`, `ind_code`, `role_id`) VALUES ('user17@gmail.com', 'a722c63db8ec8625af6cf71cb8c2d939', 'Андрій', 'Кравченко', 'Миколайович', '34567817', '4');
INSERT INTO `mydb`.`users` (`email`, `password`, `name`, `surname`, `patronymic`, `ind_code`, `role_id`) VALUES ('user18@gmail.com', 'a722c63db8ec8625af6cf71cb8c2d939', 'Назар', 'Сухий', 'Миколайович', '34567818', '4');
INSERT INTO `mydb`.`users` (`email`, `password`, `name`, `surname`, `patronymic`, `ind_code`, `role_id`) VALUES ('user19@gmail.com', 'a722c63db8ec8625af6cf71cb8c2d939', 'Інна', 'Мельник', 'Миколайович', '34567819', '4');
INSERT INTO `mydb`.`users` (`email`, `password`, `name`, `surname`, `patronymic`, `ind_code`, `role_id`) VALUES ('user20@gmail.com', 'a722c63db8ec8625af6cf71cb8c2d939', 'Ірина', 'Ковальчук', 'Леондович', '34567820', '4');
INSERT INTO `mydb`.`users` (`email`, `password`, `name`, `surname`, `patronymic`, `ind_code`, `role_id`) VALUES ('user21@gmail.com', 'a722c63db8ec8625af6cf71cb8c2d939', 'Анастасія', 'Ковальчук', 'Леондович', '34567821', '4');
INSERT INTO `mydb`.`users` (`email`, `password`, `name`, `surname`, `patronymic`, `ind_code`, `role_id`) VALUES ('user22@gmail.com', 'a722c63db8ec8625af6cf71cb8c2d939', 'Віктор', 'Шевченко', 'Ігорович', '34567822', '4');
INSERT INTO `mydb`.`users` (`email`, `password`, `name`, `surname`, `patronymic`, `ind_code`, `role_id`) VALUES ('user23@gmail.com', 'a722c63db8ec8625af6cf71cb8c2d939', 'Анатолій', 'Коваленко', 'Миколайович', '34567823', '4');
INSERT INTO `mydb`.`users` (`email`, `password`, `name`, `surname`, `patronymic`, `ind_code`, `role_id`) VALUES ('user24@gmail.com', 'a722c63db8ec8625af6cf71cb8c2d939', 'Сергій', 'Сухий', 'Леондович', '34567824', '4');
INSERT INTO `mydb`.`users` (`email`, `password`, `name`, `surname`, `patronymic`, `ind_code`, `role_id`) VALUES ('user25@gmail.com', 'a722c63db8ec8625af6cf71cb8c2d939', 'Леонід', 'Кравченко', 'Ігорович', '34567825', '4');
INSERT INTO `mydb`.`users` (`email`, `password`, `name`, `surname`, `patronymic`, `ind_code`, `role_id`) VALUES ('user26@gmail.com', 'a722c63db8ec8625af6cf71cb8c2d939', 'Микола', 'Сухий', 'Андрійович', '34567826', '4');
INSERT INTO `mydb`.`users` (`email`, `password`, `name`, `surname`, `patronymic`, `ind_code`, `role_id`) VALUES ('user27@gmail.com', 'a722c63db8ec8625af6cf71cb8c2d939', 'Вікторія', 'Чалий', 'Вікторович', '34567827', '4');
INSERT INTO `mydb`.`users` (`email`, `password`, `name`, `surname`, `patronymic`, `ind_code`, `role_id`) VALUES ('user28@gmail.com', 'a722c63db8ec8625af6cf71cb8c2d939', 'Назар', 'Бойко', 'Андрійович', '34567828', '4');
INSERT INTO `mydb`.`users` (`email`, `password`, `name`, `surname`, `patronymic`, `ind_code`, `role_id`) VALUES ('user29@gmail.com', 'a722c63db8ec8625af6cf71cb8c2d939', 'Ігор', 'Сухий', 'Миколайович', '34567829', '4');
INSERT INTO `mydb`.`users` (`email`, `password`, `name`, `surname`, `patronymic`, `ind_code`, `role_id`) VALUES ('user30@gmail.com', 'a722c63db8ec8625af6cf71cb8c2d939', 'Леонід', 'Мельник', 'Вікторович', '34567830', '4');

INSERT INTO `mydb`.`inspectors` (`login`, `password`, `name`, `surname`, `patronymic`, `role_id`) VALUES ('admin@gmail.com', 'a722c63db8ec8625af6cf71cb8c2d939', 'Леонід', 'Мельник', 'Вікторович', '1');
INSERT INTO `mydb`.`inspectors` (`login`, `password`, `name`, `surname`, `patronymic`, `role_id`) VALUES ('inspector1@gmail.com', 'a722c63db8ec8625af6cf71cb8c2d939', 'Ігор', 'Сухий', 'Іванович', '2');
INSERT INTO `mydb`.`inspectors` (`login`, `password`, `name`, `surname`, `patronymic`, `role_id`) VALUES ('inspector2@gmail.com', 'a722c63db8ec8625af6cf71cb8c2d939', 'Іван', 'Чалий', 'Васильович', '2');
INSERT INTO `mydb`.`inspectors` (`login`, `password`, `name`, `surname`, `patronymic`, `role_id`) VALUES ('inspector3@gmail.com', 'a722c63db8ec8625af6cf71cb8c2d939', 'Андрій', 'Сухий', 'Андрійович', '2');
INSERT INTO `mydb`.`inspectors` (`login`, `password`, `name`, `surname`, `patronymic`, `role_id`) VALUES ('inspector4@gmail.com', 'a722c63db8ec8625af6cf71cb8c2d939', 'Назар', 'Коваленко', 'Ігорович', '2');
INSERT INTO `mydb`.`inspectors` (`login`, `password`, `name`, `surname`, `patronymic`, `role_id`) VALUES ('inspector5@gmail.com', 'a722c63db8ec8625af6cf71cb8c2d939', 'Інна', 'Шевченко', 'Вікторович', '2');


INSERT INTO `mydb`.`inspectors_users` (`inspector_id`, `user_id`, `active`) VALUES ('2', '1', 'true');
INSERT INTO `mydb`.`inspectors_users` (`inspector_id`, `user_id`, `active`) VALUES ('2', '2', 'true');
INSERT INTO `mydb`.`inspectors_users` (`inspector_id`, `user_id`, `active`) VALUES ('2', '3', 'true');
INSERT INTO `mydb`.`inspectors_users` (`inspector_id`, `user_id`, `active`) VALUES ('2', '4', 'true');
INSERT INTO `mydb`.`inspectors_users` (`inspector_id`, `user_id`, `active`) VALUES ('2', '5', 'true');
INSERT INTO `mydb`.`inspectors_users` (`inspector_id`, `user_id`, `active`) VALUES ('2', '6', 'true');
INSERT INTO `mydb`.`inspectors_users` (`inspector_id`, `user_id`, `active`) VALUES ('2', '7', 'true');
INSERT INTO `mydb`.`inspectors_users` (`inspector_id`, `user_id`, `active`) VALUES ('2', '8', 'true');
INSERT INTO `mydb`.`inspectors_users` (`inspector_id`, `user_id`, `active`) VALUES ('2', '9', 'true');
INSERT INTO `mydb`.`inspectors_users` (`inspector_id`, `user_id`, `active`) VALUES ('3', '10', 'true');
INSERT INTO `mydb`.`inspectors_users` (`inspector_id`, `user_id`, `active`) VALUES ('3', '11', 'true');
INSERT INTO `mydb`.`inspectors_users` (`inspector_id`, `user_id`, `active`) VALUES ('3', '12', 'true');
INSERT INTO `mydb`.`inspectors_users` (`inspector_id`, `user_id`, `active`) VALUES ('3', '13', 'true');
INSERT INTO `mydb`.`inspectors_users` (`inspector_id`, `user_id`, `active`) VALUES ('3', '14', 'true');
INSERT INTO `mydb`.`inspectors_users` (`inspector_id`, `user_id`, `active`) VALUES ('3', '15', 'true');
INSERT INTO `mydb`.`inspectors_users` (`inspector_id`, `user_id`, `active`) VALUES ('4', '16', 'true');
INSERT INTO `mydb`.`inspectors_users` (`inspector_id`, `user_id`, `active`) VALUES ('4', '17', 'true');
INSERT INTO `mydb`.`inspectors_users` (`inspector_id`, `user_id`, `active`) VALUES ('4', '18', 'true');
INSERT INTO `mydb`.`inspectors_users` (`inspector_id`, `user_id`, `active`) VALUES ('4', '19', 'true');
INSERT INTO `mydb`.`inspectors_users` (`inspector_id`, `user_id`, `active`) VALUES ('4', '20', 'true');
INSERT INTO `mydb`.`inspectors_users` (`inspector_id`, `user_id`, `active`) VALUES ('4', '21', 'true');
INSERT INTO `mydb`.`inspectors_users` (`inspector_id`, `user_id`, `active`) VALUES ('4', '22', 'true');
INSERT INTO `mydb`.`inspectors_users` (`inspector_id`, `user_id`, `active`) VALUES ('5', '23', 'true');
INSERT INTO `mydb`.`inspectors_users` (`inspector_id`, `user_id`, `active`) VALUES ('5', '24', 'true');
INSERT INTO `mydb`.`inspectors_users` (`inspector_id`, `user_id`, `active`) VALUES ('5', '25', 'true');
INSERT INTO `mydb`.`inspectors_users` (`inspector_id`, `user_id`, `active`) VALUES ('5', '26', 'true');
