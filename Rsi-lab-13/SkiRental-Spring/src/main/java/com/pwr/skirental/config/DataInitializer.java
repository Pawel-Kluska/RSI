package com.pwr.skirental.config;

import com.pwr.skirental.entity.Category;
import com.pwr.skirental.entity.Equipment;
import com.pwr.skirental.repository.CategoryRepository;
import com.pwr.skirental.repository.EquipmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DataInitializer {

    private final CategoryRepository categoryRepository;
    private final EquipmentRepository equipmentRepository;


    @PostConstruct
    public void init() {
        if (categoryRepository.count() == 0) {
            categoryRepository.saveAll(getCategories());
        }
        if (equipmentRepository.count() == 0) {
            equipmentRepository.saveAll(getEquipments());
        }
    }

    private List<Category> getCategories() {
        return List.of(
                Category.builder()
                        .name("Narty")
                        .build(),
                Category.builder()
                        .name("Snowboard")
                        .build(),
                Category.builder()
                        .name("Buty narciarskie")
                        .build(),
                Category.builder()
                        .name("Buty snowboardowe")
                        .build(),
                Category.builder()
                        .name("Kaski")
                        .build(),
                Category.builder()
                        .name("Gogle")
                        .build(),
                Category.builder()
                        .name("Kijki")
                        .build()
        );
    }

    private List<Equipment> getEquipments() {
        return List.of(
                Equipment.builder()
                        .name("Narty Atomic Redster X9")
                        .description("Model dla zaawansowanych narciarzy")
                        .price(100)
                        .size("L")
                        .isBorrowed(false)
                        .image("https://kmsport.pl/2347-large_default/narty-volkl-racetiger-sl-2022-wiazania-marker-rmotion2-12-gw.jpg")
                        .category(categoryRepository.findByName("Narty").get())
                        .build(),
                Equipment.builder()
                        .name("Narty Atomic Redster X7")
                        .description("Model dla początkujących narciarzy")
                        .price(80.99)
                        .size("M")
                        .isBorrowed(false)
                        .image("https://olimpiasport.pl/upload/79/olimpiadb_79748_d_d.jpg")
                        .category(categoryRepository.findByName("Narty").get())
                        .build(),
                Equipment.builder()
                        .name("Snowboard Burton Custom")
                        .description("Model dla zaawansowanych snowboardzistów")
                        .price(65.50)
                        .size("L")
                        .isBorrowed(false)
                        .image("https://snowboardok.pl/21149-large_default/p1530-snowboard-jones-w-airheart.jpg")
                        .category(categoryRepository.findByName("Snowboard").get())
                        .build(),
                Equipment.builder()
                        .name("Kask narciarski POC Obex Spin")
                        .description("Lekki wygodny kask dla początkujących")
                        .price(23.80)
                        .size("S")
                        .isBorrowed(false)
                        .image("https://deshop.pro/wp-content/uploads/2020/09/30B4694_E533_A.jpg")
                        .category(categoryRepository.findByName("Kaski").get())
                        .build(),
                Equipment.builder()
                        .name("Gogle narciarskie Oakley Flight Deck XM")
                        .description("Google z przycimnianą szybką")
                        .price(10)
                        .size("XS")
                        .isBorrowed(false)
                        .image("https://www.gotravels.pl/images/Gogle/G_101/Gogle_narciarskie_Arctica_G-101C.jpg")
                        .category(categoryRepository.findByName("Gogle").get())
                        .build(),
                Equipment.builder()
                        .name("Kijki narciarskie Leki Worldcup Racing SL TBS")
                        .description("Lekkie solidne kijki dla zaawansowanych narciarzy")
                        .price(15)
                        .size("XL")
                        .isBorrowed(false)
                        .image("https://www.projektjunior.pl/21071-large_default/kije-narciarskie-dla-dzieci-rossignol-telescopic-jr-.jpg")
                        .category(categoryRepository.findByName("Kijki").get())
                        .build(),
                Equipment.builder()
                        .name("Buty narciarskie Atomic Hawx Prime 120 S")
                        .description("Buty dla profesjonalistów")
                        .price(30)
                        .size("M")
                        .isBorrowed(false)
                        .image("https://ski24.pl/117960-large_default/buty-narciarskie-salomon-spro-r100.jpg")
                        .category(categoryRepository.findByName("Buty narciarskie").get())
                        .build(),
                Equipment.builder()
                        .name("Buty narciarskie Atomic Hawx Prime 110 S")
                        .description("Buty dla średnio zaawansowanych narciarzy")
                        .price(45)
                        .size("S")
                        .isBorrowed(false)
                        .image("https://ski24.pl/117960-large_default/buty-narciarskie-salomon-spro-r100.jpg")
                        .category(categoryRepository.findByName("Buty narciarskie").get())
                        .build(),
                Equipment.builder()
                        .name("Kask narciarski POC Obex BC Spin")
                        .description("Kask ze wzmocnioną konstrukcją dla zaawansowanych narciarzy")
                        .price(20)
                        .size("L")
                        .isBorrowed(false)
                        .image("https://deshop.pro/wp-content/uploads/2020/09/30B4694_E533_A.jpg")
                        .category(categoryRepository.findByName("Kaski").get())
                        .build(),
                Equipment.builder()
                        .name("Kask narciarski POC Obex Pure")
                        .description("Kask dla początkujących narciarzy")
                        .price(15.3)
                        .size("L")
                        .isBorrowed(false)
                        .image("https://deshop.pro/wp-content/uploads/2020/09/30B4694_E533_A.jpg")
                        .category(categoryRepository.findByName("Kaski").get())
                        .build(),
                Equipment.builder()
                        .name("Snowboard Burton Custom Flying V")
                        .description("Snowboard dla początkujących snowboardzistów")
                        .price(70)
                        .size("M")
                        .isBorrowed(false)
                        .image("https://snowboardok.pl/21149-large_default/p1530-snowboard-jones-w-airheart.jpg")
                        .category(categoryRepository.findByName("Snowboard").get())
                        .build(),
                Equipment.builder()
                        .name("Snowboard Burton Custom X")
                        .description("Snowboard dla zaawansowanych snowboardzistów")
                        .price(90)
                        .size("L")
                        .isBorrowed(false)
                        .image("https://snowboardok.pl/21149-large_default/p1530-snowboard-jones-w-airheart.jpg")
                        .category(categoryRepository.findByName("Snowboard").get())
                        .build(),
                Equipment.builder()
                        .name("Narty Atomic Redster X9 S FT")
                        .description("Narty slaomowe dla zaawansowanych narciarzy")
                        .price(200)
                        .size("XL")
                        .isBorrowed(false)
                        .image("https://olimpiasport.pl/upload/79/olimpiadb_79748_d_d.jpg")
                        .category(categoryRepository.findByName("Narty").get())
                        .build());
    }
}
