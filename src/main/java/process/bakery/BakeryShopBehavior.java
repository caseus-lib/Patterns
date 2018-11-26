package process.bakery;

import enums.ProductType;
import enums.TimesOfDay;
import environment.creatures.OrdinalCreature;
import environment.products.SweetBox;

public interface BakeryShopBehavior {

    void assignNewOrdinalCreature(OrdinalCreature ordinalCreature);
    int generateOrderNumber();
    void acceptOrder();
    String retrieveCreatureInformation();
    void askForProductAmount(ProductType productType, int amount);
    String retrieveOrderInformation();
    void destroyAll();
    void recoverAll();
    void setTimesOfDay(TimesOfDay timesOfDay);
    void visitCreature();
    String getJournalText();
    SweetBox askForBox();
    void saleBox();

}
