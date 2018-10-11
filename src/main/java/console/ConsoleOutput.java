package console;

import app.Steps;
import creatures.OrdinalCreature;
import creatures.Person;
import creatures.Unicorn;
import magical.kitchen.BoxMaker;
import magical.kitchen.ShowCase;
import magical.powers.MagicalPower;
import magical.products.Product;
import magical.products.SpiceDecorator;
import sale.Bakery;
import sale.MagicalAdapter;
import sale.SellerProxy;
import steps.StepsWorker;

import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ConsoleOutput implements Steps {

    private StepsWorker stepsWorker;

    private Bakery seller = new SellerProxy();
    private Product product;
    private MagicalPower magicalPower;
    private OrdinalCreature ordinalCreature;
    private boolean getBox = false;
    private Scanner scanner = new Scanner(System.in);

    private void printComment(String comment) {
        System.out.println("\n------" + comment + "------\n");
    }

    private void printCustomerReplica(String replica) {
        System.out.println("(Покупатель):\t " + replica + "\n");
    }

    private void printSellerReplica(String replica) {
        System.out.println("(Продавец):\t" + replica + "\n");
    }

    private String waitForPress() {
        return scanner.next();
    }

    public void startProcess() {
        stepsWorker = new StepsWorker(this);
        do {
            stepsWorker.nextStep();
        } while (!waitForPress().equals("quit"));
    }

    @Override
    public void girlHasCame() {
        printComment("Заходит девушка");
        printCustomerReplica("Добрый день!");
        ordinalCreature = new Person("Мария");
    }

    @Override
    public void boyHasCame() {
        printComment("Заходит парень");
        printCustomerReplica("Здравствуйте!");
        ordinalCreature = new Person("Павел");
    }

    @Override
    public void unicornHasCame() {
        printComment("Заходит единорог");
        printCustomerReplica("Gjkzdadioadk!");
        ordinalCreature = new MagicalAdapter(new Unicorn());
    }

    @Override
    public void personRequestsForMinPower() {
        magicalPower = ordinalCreature.getMinPower();
        printCustomerReplica("Мои силы: \n" + ordinalCreature.toString() +
                "Мне бы добавить себе такой силы, как: " + magicalPower.getName());
        getBox = false;
    }

    @Override
    public void personRequestsForMinPowerWithSpice() {
        magicalPower = ordinalCreature.getMinPower();
        printCustomerReplica("Мои силы: \n" + ordinalCreature.toString() +
                "Мне бы добавить себе такой силы, как: " + magicalPower.getName() +
                "\nИ обязательно добавить специй!");
        getBox = false;
    }

    @Override
    public void personRequestsForBox() {
        getBox = true;
        printCustomerReplica("Соберите мне, пожалуйста, волшебную коробку со сладостями");
    }

    @Override
    public void unicornRequestsForRainbow() {
        getBox = false;
        printCustomerReplica("Tsejf ksjdfuwn jfuiwkw kdmkfs");
    }

    @Override
    public void sellerGivesProduct() {
        if (getBox) {
            product = BoxMaker.createRandomBox();
            printSellerReplica(
                    "Держите " + product.toString() + "\n" +
                            "Состав коробки:\n" + product.getInfoAboutComponents()
            );
            printComment("На прилавке появляется сладость");
        } else {
            Optional<Product> optionalProduct = seller.hasProduct(magicalPower);
            if (optionalProduct.isPresent()) {
                product = optionalProduct.get();
                seller.saleProduct(product);
                printSellerReplica("Держите " + product.toString());
                printComment("На прилавке появляется сладость");
            } else {
                printSellerReplica("К сожалению, все законичилось. Приходите завтра!");
            }
        }
    }

    @Override
    public void sellerGivesProductWithSpice() {
        Optional<Product> optionalProduct = seller.hasProduct(magicalPower);
        if (optionalProduct.isPresent()) {
            product = optionalProduct.get();
            seller.saleProduct(product);
            String message = "Без специй:\n" + product.getName() + "\n" + product.getMagicalPowerList().toString();
            product = new SpiceDecorator(product);
            printSellerReplica(message + "\nСо специями:\n" +
                    product.getName() + "\n" + product.getMagicalPowerList().toString());
            printComment("На прилавке появляется сладость");
        } else {
            printSellerReplica("К сожалению, все законичилось. Приходите завтра!");
        }
    }

    @Override
    public void personGetsProduct() {
        ordinalCreature.consume(product);
        printCustomerReplica("Спасибо! Теперь мои силы: \n" + ordinalCreature.toString());
    }

    @Override
    public void unicornGetsProduct() {
        printComment("Волшебная пыль превращаетя в радугу");
        ordinalCreature.consume(product);
        printCustomerReplica("Thfjsnfussk!");
    }

    @Override
    public void watchShowCase() {
        printComment("Открывается прилавок");
        printComment("Его содержимое" +
                ShowCase.getInstance().getAllProducts()
                        .stream()
                        .map(bake -> bake.getName() + "\n" + bake.getInfoAboutComponents()).
                        collect(Collectors.joining("\n"))
        );
    }

    @Override
    public void adapterWorks() {
        printComment("Сладоть превращаетя в волшебную пыль");
    }
}
