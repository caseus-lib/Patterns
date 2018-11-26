package process.hall;

import environment.creatures.Person;
import environment.sale.MagicalAdapter;

import java.util.ArrayList;
import java.util.List;

public class JournalVisitor implements Visitor {

    private List<String> journal = new ArrayList<>();

    @Override
    public void visit(Person person) {
        journal.add("- Как ваше имя?");
        journal.add("- " + person.getName());
        journal.add("- Каких сил у вас меньше всего?");
        journal.add("- " + person.getMinPower().getName());
        journal.add("");
    }

    @Override
    public void visit(MagicalAdapter unicorn) {
        journal.add("- EWojif hjksf&");
        journal.add("- " + "Sjfjer");
        journal.add("- Wpfisnfsk");
        journal.add("- " + "ASfahfv shdf kjwaef fifs!");
        journal.add("");
    }

    public List<String> getJournal() {
        return journal;
    }
}
