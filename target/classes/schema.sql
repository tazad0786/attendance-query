CREATE TABLE IF NOT EXISTS ATTENDANCE_INFO (id INT NOT NULL AUTO_INCREMENT, employee_id INT, employee_name VARCHAR(255), swipe_in TIME, swipe_out TIME, total_hours TIME, status VARCHAR(255), date DATE, office_location VARCHAR(255), PRIMARY KEY (id));