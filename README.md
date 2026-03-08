# Hệ Thống Quản Lý Ứng Viên - Ứng dụng Design Patterns

## 📖 Tổng Quan Dự Án

Đây là dự án **Hệ Thống Quản Lý Ứng Viên** được xây dựng bằng ngôn ngữ **Java**. Dự án này thể hiện một thiết kế hướng đối tượng nâng cao bằng cách tự triển khai các Design Pattern thông dụng từ đầu. Mục tiêu chính của kho lưu trữ này không chỉ ở việc quản lý thông tin ứng viên, mà là để thể hiện kỹ năng áp dụng thực tế các nguyên tắc kỹ nghệ phần mềm như **Factory Method**, tự viết bộ chứa **Dependency Injection (DI)**, và kiến trúc **MVC (Model-View-Controller)**.

## 🚀 Công Nghệ & Khái Niệm Sử Dụng

- **Ngôn ngữ:** Java 21
- **Công cụ Build:** Maven
- **Kiến trúc:** MVC (Model, View, Controller)
- **Các Design Pattern / Khái Niệm Cốt Lõi:**
  - **Factory Method Pattern:** Được triển khai trong lớp `CandidateFactory` để xử lý việc khởi tạo các loại ứng viên khác nhau (Experience, Intern, Fresher). Điểm đặc biệt là dự án sử dụng **Java Reflection** để tự động quét toàn bộ package `creator` lúc chạy (runtime) và tự động đăng ký các lớp Creator vào Factory. Nhờ đó, dự án tuân thủ chặt chẽ Nguyên lý Đóng-Mở (Open-Closed Principle - OCP).
  - **Dependency Injection (Custom IoC Container):** Một bộ chứa (Container) DI tùy chỉnh, gọn nhẹ được tự viết trong `com.phongvu.gof.di.Container`. Nó sẽ tự động khởi tạo các component đã đăng ký dưới dạng Singleton và tự động tiêm dependencies (inject dependencies) vào các biến được đánh dấu bằng `@Inject` thông qua Reflection.
  - **Data Transfer Object (DTO):** Dùng đối tượng `CandidateDTO` để vận chuyển dữ liệu qua lại giữa tầng Giao diện (UI), Tầng Controller và tầng Domain (Model).
  - **Xử Lý Ngoại Lệ Tùy Chỉnh:** Gom chung các lỗi xảy ra bằng Custom Exception `AppException`.

## 📂 Cấu Trúc Dự Án

```text
src/main/java/com/phongvu/gof/
├── constant/        # Các Enums và Hằng số (VD: CandidateType)
├── controller/      # Logic xử lý chính và điều hướng (CandidateController)
├── creator/         # Các Creator kế thừa Factory để tạo đối tượng (ExperienceCreator, InternCreator, ...)
├── di/              # Triển khai Dependency Injection Container tùy chỉnh (Container, @Inject)
├── dto/             # Data Transfer Objects (CandidateDTO)
├── factory/         # Nơi chứa logic chung của Factory Method (CandidateFactory)
├── model/           # Các lớp thực thể Domain (Candidate, Experience, Intern, ...)
├── utils/           # Các lớp tiện ích (Validation, Xử lý Exception)
├── view/            # Tầng hiển thị dữ liệu ra màn hình Console (CandidateView)
└── Main.java        # Điểm bắt đầu (Entry point) của ứng dụng
```

### Điểm Sáng Trong Thiết Kế:
1. **The Factory (`CandidateFactory`):** 
   Thay vì dùng một cấu trúc `switch-case` hoặc `if-else` dài dằng dặc để khởi tạo đối tượng, hàm tạo (constructor) của Factory sẽ tự quét các file `.class` trong thư mục `creator` ở runtime. Bất cứ lớp nào implements `CandidateCreator` sẽ được hệ thống động tự bắt (auto-register) vào một danh sách Map theo đúng kiểu ứng viên mà lớp đó hỗ trợ.
2. **The DI Container (`Container`):**
   Trong phương thức `Main.java`, các lớp chủ chốt (`CandidateView`, `CandidateFactory`, `CandidateController`) được tự tay ta đưa vào để đăng ký vòng đời. Lệnh `Container.init()` sau đó gọi tới Reflection để tự gọi hàm new() (Singleton) cho các lớp này và gắn chúng lại với nhau một cách mượt mà ở các biến có mác `@Inject` mà bạn chả cần phải tiêm tay.

## ⚙️ Hướng Dẫn Chạy

### Yêu Cầu Cài Đặt
- Java Development Kit (JDK) 21 hoặc mới hơn.
- Công cụ Maven.

### Các Bước Thực Hiện
1. Clone dự án về máy:
   ```bash
   git clone <repository_url>
   ```
2. Mở thư mục dự án:
   ```bash
   cd CandidateMgrDesignFactory
   ```
3. Build dự án bằng Maven:
   ```bash
   mvn clean install
   ```
4. Chạy ứng dụng:
   ```bash
   # Bạn có thể chạy file Main.java trực tiếp trên IDE (IntelliJ, Eclipse...) hoặc dùng lệnh sau:
   mvn exec:java -Dexec.mainClass="com.phongvu.gof.Main"
   ```

## 👨‍💻 Đánh Giá Từ Góc Độ Kỹ Nghệ Phần Mềm
Dưới lăng kính của một kỹ sư phần mềm, việc đọc dự án này cho thấy bạn (tác giả) đang rất chú trọng đến kiến trúc có thể mở rộng và dễ bảo trì. Việc chủ động tránh xa những đoạn code khởi tạo rườm rà (hard-code instantiation) hay tiêm-dependecy-bằng-tay, thay vào đó là sử dụng khéo léo Metadata (Reflection) là một điểm cộng lớn. Thiết kế này giúp hệ thống trở nên cực kỳ linh hoạt — ví dụ, để thêm một loại ứng viên mới (ví dụ Fresher), bạn chỉ cần tạo lớp Model mới, tạo Creator mới mà không cần phải thay đổi mã nguồn (không chạm vào) lớp `CandidateFactory` hay Controller cốt lõi.
