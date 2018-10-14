package app.console;

import app.console.view.ProductDescriptionFactory;
import app.executor.Behavior;
import app.executor.BehaviorExecutor;
import app.executor.Steps;
import app.executor.StepsWorker;
import app.graphic.ui.services.Size;
import environment.creatures.Person;
import environment.creatures.Unicorn;
import environment.kitchen.ShowCase;
import environment.magical.powers.MagicalPower;
import environment.products.Product;
import environment.sale.MagicalAdapter;
import exception.NoProductFound;
import printer.ProductViewFactory;

import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ConsoleOutput implements Steps {

    private Behavior executor = new BehaviorExecutor();
    private Scanner scanner = new Scanner(System.in);
    private ProductViewFactory productViewFactory = ProductDescriptionFactory.getInstance();

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
                    "Держите " + productViewFactory.getProductImage(product, new Size(0, 0)) + "\n" +
                            "Состав коробки:\n" + product.getInfoAboutComponents()
            );
            printComment("На прилавке появляется коробка");
        } else {
            Optional<Product> optionalProduct = executor.askForProduct();
            if (optionalProduct.isPresent()) {
                Product product = optionalProduct.get();
                executor.saleProduct(product);
                printSellerReplica("Держите " + productViewFactory.getProductImage(product, new Size(0, 0)));
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
            String message = "Без специй:\n" +
                    product.getName()
                    + "\n" + product.getMagicalPowerList().toString();
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
                ShowCase.getInstance().getProductAmountMap().entrySet()
                        .stream()
                        .map(entry -> {
                            Product product = ShowCase.getInstance().getByName(entry.getKey())
                                    .orElseThrow(() -> new NoProductFound(entry.getKey()));
                            return productViewFactory.getProductImage(product,
                                    new Size(100, 100))
                                    + "\n количество:" + entry.getValue()
                                    + "\n" + product.getInfoAboutComponents();
                        })
                        .collect(Collectors.joining("\n\n"))
        );
    }

    @Override
    public void adapterWorks() {
        printComment("Сладоть превращаетя в волшебную пыль");
    }
}
