package com.odintsov.wallpapers_project.initializers;

import com.odintsov.wallpapers_project.domain.entities.PrintMethod;
import com.odintsov.wallpapers_project.domain.entities.Printing;
import com.odintsov.wallpapers_project.domain.entities.PrintingMethodsLink;
import com.odintsov.wallpapers_project.domain.repositories.PrintingMethodLinkRepository;
import com.odintsov.wallpapers_project.domain.repositories.PrintingMethodRepository;
import com.odintsov.wallpapers_project.domain.repositories.PrintingRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class PrintingInitializer {

    private final PrintingRepository printingRepository;
    private final PrintingMethodRepository methodsRepository;
    private final PrintingMethodLinkRepository linkRepository;


    public PrintingInitializer(PrintingRepository printingRepository,
                               PrintingMethodRepository methodsRepository,
                               PrintingMethodLinkRepository linkRepository) {
        this.printingRepository = printingRepository;
        this.methodsRepository = methodsRepository;
        this.linkRepository = linkRepository;
    }

    @PostConstruct
    public void initPrintings() {
        createMethods();
        createPrintings();
    }

    private void createMethods() {
        List<PrintMethod> methods = methodsRepository.findAll();
        if (methods.size() >= 2) return;

        PrintMethod m1 = new PrintMethod();
        m1.setName("Inkjet");
        m1.setDeadline(3);

        PrintMethod m2 = new PrintMethod();
        m2.setName("Laser");
        m2.setDeadline(2);

        methodsRepository.saveAll(Arrays.asList(m1, m2));
    }

    private void createPrintings() {
        if (printingRepository.count() > 0) {
            return;
        }

        List<PrintMethod> methods = methodsRepository.findAll();
        if (methods.isEmpty()) {
            throw new IllegalStateException("Print methods should be initialized first");
        }

        Printing p1 = Printing.builder()
                .name("Classic Print")
                .article("PR-002")
                .basePrice(29.99f)
                .salePrice(24.99f)
                .image("/images/classic_print.jpg")
                .description("High-quality classic print for interiors")
                .quantity(50)
                .build();

        Printing saved = printingRepository.save(p1);

        linkRepository.saveAll(List.of(
                PrintingMethodsLink.builder().printing(saved).method(methods.get(0)).build(),
                PrintingMethodsLink.builder().printing(saved).method(methods.get(1)).build()
        ));
    }
}
