CREATE DATABASE TTIKUS
USE TTIKUS

CREATE TABLE users (
    id VARCHAR(50) PRIMARY KEY,
    full_name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    role VARCHAR(20) NOT NULL CHECK (role IN ('MANAGER', 'STAFF', 'ADMIN'))
);

CREATE TABLE projects (
    id VARCHAR(50) PRIMARY KEY,
    name VARCHAR(150) NOT NULL,
    description TEXT,
    start_date DATE
);

CREATE TABLE tasks (
    id VARCHAR(50) PRIMARY KEY,
    title VARCHAR(200) NOT NULL,
    status VARCHAR(20) DEFAULT 'OPEN' CHECK (status IN ('OPEN', 'IN_PROGRESS', 'REVIEW', 'DONE', 'CANCELLED')),
    priority VARCHAR(20) DEFAULT 'MEDIUM' CHECK (priority IN ('LOW', 'MEDIUM', 'HIGH')),
    due_date DATE,
    project_id VARCHAR(50),
    assignee_id VARCHAR(50),
    
    CONSTRAINT fk_task_project FOREIGN KEY (project_id) REFERENCES projects(id) ON DELETE CASCADE,
    CONSTRAINT fk_task_user FOREIGN KEY (assignee_id) REFERENCES users(id) ON DELETE SET NULL
);

CREATE INDEX idx_task_status ON tasks(status);
CREATE INDEX idx_task_assignee ON tasks(assignee_id);

-- Thêm Users
INSERT INTO users (id, full_name, email, role) VALUES
('U1', 'Nguyen Van Manager', 'manager@test.com', 'MANAGER'),
('U2', 'Tran Van Staff', 'staff1@test.com', 'STAFF'),
('U3', 'Le Thi Staff', 'staff2@test.com', 'STAFF'),
('U4', 'Pham Van Dev', 'dev@test.com', 'STAFF');

-- Thêm Projects
INSERT INTO projects (id, name, description, start_date) VALUES
('PRJ1', 'Website E-commerce', 'Xây dựng web bán hàng', '2024-01-01'),
('PRJ2', 'App Mobile Banking', 'App ngân hàng số', '2024-02-15');

-- Thêm Tasks (30 records)
INSERT INTO tasks (id, title, status, priority, project_id, assignee_id, due_date) VALUES
('T01', 'Thiết kế Database', 'DONE', 'HIGH', 'PRJ1', 'U1', '2024-01-05'),
('T02', 'Dựng khung Project', 'DONE', 'HIGH', 'PRJ1', 'U2', '2024-01-06'),
('T03', 'Code API Login', 'IN_PROGRESS', 'HIGH', 'PRJ1', 'U2', '2024-01-10'),
('T04', 'Code API Register', 'IN_PROGRESS', 'MEDIUM', 'PRJ1', 'U3', '2024-01-10'),
('T05', 'Thiết kế UI Trang chủ', 'REVIEW', 'MEDIUM', 'PRJ1', 'U4', '2024-01-12'),
('T06', 'Cắt HTML/CSS', 'OPEN', 'LOW', 'PRJ1', 'U3', '2024-01-15'),
('T07', 'Viết Unit Test', 'OPEN', 'LOW', 'PRJ1', 'U2', '2024-01-20'),
('T08', 'Deploy Server Test', 'OPEN', 'HIGH', 'PRJ1', 'U1', '2024-01-25'),
('T09', 'Họp team đầu tuần', 'DONE', 'LOW', 'PRJ1', 'U1', '2024-01-02'),
('T10', 'Mua server AWS', 'DONE', 'MEDIUM', 'PRJ1', 'U1', '2024-01-03'),
('T11', 'Phân tích App Mobile', 'DONE', 'HIGH', 'PRJ2', 'U1', '2024-02-16'),
('T12', 'Thiết kế màn hình Login', 'IN_PROGRESS', 'MEDIUM', 'PRJ2', 'U4', '2024-02-20'),
('T13', 'Thiết kế màn hình Dashboard', 'OPEN', 'MEDIUM', 'PRJ2', 'U4', '2024-02-22'),
('T14', 'Code chức năng Chuyển tiền', 'OPEN', 'HIGH', 'PRJ2', 'U2', '2024-03-01'),
('T15', 'Code chức năng Nạp tiền', 'OPEN', 'HIGH', 'PRJ2', 'U3', '2024-03-01'),
('T16', 'Tích hợp Payment Gateway', 'OPEN', 'HIGH', 'PRJ1', 'U2', '2024-02-05'),
('T17', 'Fix bug giao diện mobile', 'REVIEW', 'MEDIUM', 'PRJ1', 'U3', '2024-02-06'),
('T18', 'Tối ưu câu lệnh SQL', 'IN_PROGRESS', 'HIGH', 'PRJ1', 'U2', '2024-02-07'),
('T19', 'Viết tài liệu hướng dẫn', 'OPEN', 'LOW', 'PRJ1', 'U4', '2024-02-10'),
('T20', 'Backup dữ liệu', 'DONE', 'MEDIUM', 'PRJ1', 'U1', '2024-01-30'),
('T21', 'Setup CI/CD Pipeline', 'IN_PROGRESS', 'HIGH', 'PRJ2', 'U1', '2024-02-25'),
('T22', 'Review code tuần 1', 'DONE', 'MEDIUM', 'PRJ2', 'U1', '2024-02-28'),
('T23', 'Họp khách hàng', 'DONE', 'HIGH', 'PRJ2', 'U1', '2024-02-15'),
('T24', 'Mua license phần mềm', 'OPEN', 'LOW', 'PRJ2', 'U1', '2024-03-05'),
('T25', 'Training nhân sự mới', 'OPEN', 'MEDIUM', 'PRJ1', 'U2', '2024-02-10'),
('T26', 'Fix lỗi bảo mật', 'OPEN', 'HIGH', 'PRJ1', 'U3', '2024-02-12'),
('T27', 'Nâng cấp thư viện', 'OPEN', 'LOW', 'PRJ1', 'U4', '2024-02-15'),
('T28', 'Kiểm thử hiệu năng', 'OPEN', 'MEDIUM', 'PRJ2', 'U3', '2024-03-10'),
('T29', 'Viết API tài liệu', 'OPEN', 'LOW', 'PRJ2', 'U4', '2024-03-12'),
('T30', 'Tổng kết dự án Phase 1', 'OPEN', 'HIGH', 'PRJ1', 'U1', '2024-03-20');

SELECT t.id, t.title, t.status, p.name as project_name
FROM tasks t
JOIN users u ON t.assignee_id = u.id
JOIN projects p ON t.project_id = p.id
WHERE u.id = 'U2';

SELECT id, title, status, priority, due_date
FROM tasks
WHERE project_id = 'PRJ1'
ORDER BY due_date ASC;

SELECT status, COUNT(*) as total_tasks
FROM tasks
GROUP BY status;