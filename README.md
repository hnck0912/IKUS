Đây là dự án backend quản lý công việc mình tự phát triển bằng Spring Boot. Ứng dụng cung cấp các RESTful API để quản lý dự án, giao việc cho nhân viên và theo dõi tiến độ.

- Các chức năng chính: 

 Quản lý tài khoản: Đăng ký, đăng nhập và bảo mật bằng JWT token.

 Phân quyền (Role-based): Sử dụng Spring Security để chia quyền (ví dụ: chỉ tài khoản Manager mới được phép tạo dự án mới).

 Quản lý dự án (Project): Thêm, sửa, xem, xóa thông tin dự án.

 Quản lý công việc (Task): Tạo task, thiết lập deadline, giao việc cho user cụ thể và cập nhật trạng thái (TODO, IN_PROGRESS, DONE).

 Validate dữ liệu: Chuẩn hóa dữ liệu đầu vào và bắt lỗi tập trung (Global Exception Handling) để trả về message thân thiện.

- Stack công nghệ:

 Ngôn ngữ: Java 17

 Framework: Spring Boot 3.x

 Database: SQL Server

 Authentication: Spring Security & JWT

 Document API: Swagger UI

- Hướng dẫn chạy dự án ở máy Local:

B1 Clone source code về máy.

B2 Mở SQL Server và tạo một database mới tên là `TTIKUS`.

B3 Mở file cấu hình `src/main/resources/application-dev.properties`, sửa lại config kết nối database (username, password) cho khớp với máy của bạn.

B4 Build và chạy dự án thông qua class `IkusApplication.java` hoặc dùng Maven Wrapper.

B5 Sau khi server start ở port 8080, mở trình duyệt truy cập vào `http://localhost:8080/swagger-ui/index.html` để xem danh sách API và test trực tiếp.
