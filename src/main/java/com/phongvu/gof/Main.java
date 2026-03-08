package com.phongvu.gof;

import com.phongvu.gof.controller.CandidateController;
import com.phongvu.gof.di.Container;
import com.phongvu.gof.dto.CandidateDTO;
import com.phongvu.gof.factory.CandidateFactory;
import com.phongvu.gof.utils.validation.Validation;
import com.phongvu.gof.view.CandidateView;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            // ===== ĐĂNG KÝ VÀ KHỞI ĐỘNG DI BẰNG REFLECTION =====
            // Khai báo các class chủ chốt cần DI Container tiêm giúp
            Container.register(CandidateView.class);
            Container.register(CandidateFactory.class);
            Container.register(CandidateController.class);

            // Lệnh này sẽ tự new() 3 class trên và gắn chúng vào nhau dựa vào @Inject
            Container.init();

            // ===== CHƯƠNG TRÌNH CHÍNH =====
            // Kéo Controller ra để dùng (Controller này đã được tiêm Factory và View sẵn bên trong)
            CandidateController controller = (CandidateController) Container.get(CandidateController.class);

            Scanner scanner = new Scanner(System.in);
            CandidateDTO dto = new CandidateDTO();
            int choice;

            do {
                System.out.print("The choice (1: Experience): ");
                choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        do {
                            dto.setId(Validation.getString("Enter Candidate ID: "));
                            dto.setFirstName(Validation.getString("Enter First Name: "));
                            dto.setLastName(Validation.getString("Enter Last Name: "));
                            dto.setBirthDate(Validation.getInteger("Enter Birth Date: ", 1900, 2025));
                            dto.setAddress(Validation.getString("Enter Address: "));
                            dto.setPhone(Validation.getString("Enter Phone: "));
                            dto.setEmail(Validation.getString("Enter Email: "));
                            dto.setTypeCandidate(0);
                            dto.setYearExperience(
                                    Validation.getInteger("Enter Years of Experience: ", 0, 100)
                            );
                            dto.setProfessionalSkill(
                                    Validation.getString("Enter Professional Skill: ")
                            );
                            controller.addCandidate(controller.create(dto));
                        } while (Validation.getYN("Do you want to continue (Y/N)? "));
                        break;

                    case 2:
                        // fresher – tương tự
                        break;

                    case 3:
                        // intern – tương tự
                        break;

                    case 4:
                        controller.printListNameCandidate();
                        break;
                }
            } while (true);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Lỗi khởi chạy hệ thống: " + e.getMessage());
        }
    }
}