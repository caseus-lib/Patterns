package console;

import app.Steps;
import core.Infrastructure;
import core.Executor;
import creatures.Person;
import creatures.Unicorn;
import magical.kitchen.ShowCase;
import magical.powers.MagicalPower;
import magical.products.Product;
import sale.MagicalAdapter;
import steps.StepsWorker;

import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ConsoleOutput implements Steps {

    private Infrastructure executor = new Executor();
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
        StepsWorker stepsWorker = new StepsWorker(this);
        do {
            stepsWorker.nextStep();
        } while (!waitForPress().equals("quit"));
    }

    @Override
    public void girlHasCame() {
        printComment("Заходит девушка");
        printCustomerReplica("Добрый день!");
        executor.assignNewOrdinalCreature(new Person("Мария"));
    }

    @Override
    public void boyHasCame() {
        printComment("Заходит парень");
        printCustomerReplica("Здравствуйте!");
        executor.assignNewOrdinalCreature(new Person("Павел"));
    }

    @Override
    public void unicornHasCame() {
        printComment("Заходит единорог");
        printCustomerReplica("Gjkzdadioadk!");
        executor.assignNewOrdinalCreature(new MagicalAdapter(new Unicorn()));
    }

    @Override
    public void personRequestsForMinPower() {
        MagicalPower magicalPower = executor.retrieveOrdinalCreatureMinPower();
        printCustomerReplica("Мои силы: \n" + executor.retrieveCreatureInformation() +
                "Мне бы добавить себе такой силы, как: " + magicalPower.getName());
        executor.setBoxActiveState(false);
    }

    @Override
    public void personRequestsForMinPowerWithSpice() {
        MagicalPower magicalPower = executor.retrieveOrdinalCreatureMinPower();
        printCustomerReplica("Мои силы: \n" + executor.retrieveCreatureInformation() +
                "Мне бы добавить себе такой силы, как: " + magicalPower.getName() +
                "\nИ обязательно добавить специй!");
        executor.setBoxActiveState(false);
    }

    @Override
    public void personRequestsForBox() {
        executor.setBoxActiveState(true);
        printCustomerReplica("Соберите мне, пожалуйста, волшебную коробку со сладостями");
    }

    @Override
    public void unicornRequestsForRainbow() {
        executor.setBoxActiveState(false);
        printCustomerReplica("Tsejf ksjdfuwn jfuiwkw kdmkfs");
    }

    @Override
    public void sellerGivesProduct() {
        if (executor.isBoxActive()) {
            Product product = executor.askForBox();
            printSellerReplica(
                    "Держите " + product.toString() + "\n" +
                            "Состав коробки:\n" + product.getInfoAboutComponents()
            );
            printComment("На прилавке появляется сладость");
        } else {
            Optional<Product> optionalProduct = executor.askForProduct();
            if (optionalProduct.isPresent()) {
                Product product = optionalProduct.get();
                executor.saleProduct(product);
                printSellerReplica("Держите " + product.toString());
                printComment("На прилавке появляется сладость");
            } else {
                printSellerReplica("К сожалению, все законичилось. Приходите завтра!");
            }
        }
    }

    @Override
    public void sellerGivesProductWithSpice() {
        Optional<Product> optionalProduct = executor.askForProduct();
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            executor.saleProduct(product);
            String message = "Без специй:\n" + product.getName() + "\n" + product.getMagicalPowerList().toString();
            product = executor.decorateProduct();
            printSellerReplica(message + "\nСо специями:\n" +
                    product.getName() + "\n" + product.getMagicalPowerList().toString());
            printComment("На прилавке появляется сладость");
        } else {
            printSellerReplica("К сожалению, все законичилось. Приходите завтра!");
        }
    }

    @Override
    public void personGetsProduct() {
        executor.consumeProduct();
        printCustomerReplica("Спасибо! Теперь мои силы: \n" + executor.retrieveCreatureInformation());
    }

    @Override
    public void unicornGetsProduct() {
        printComment("Волшебная пыль превращаетя в радугу");
        executor.consumeProduct();
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
