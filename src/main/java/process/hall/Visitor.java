package process.hall;

import environment.creatures.Person;
import environment.sale.MagicalAdapter;

public interface Visitor {

    void visit(Person person);
    void visit(MagicalAdapter unicorn);

}
