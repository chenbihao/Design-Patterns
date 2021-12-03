import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import src.flyweight.Dice;
import src.flyweight.DiceFactory;
import src.flyweight.DiceType;
import src.template.Computer;
import src.template.GamingComputer;
import src.template.OfficeComputer;
import src.template.ProgrammingComputer;

import java.util.AbstractList;

/**
 * @author: chenbihao
 * @create: 2021/9/6
 * @Description:
 * @History:
 */
public class TemplateTest {

    @Test
    public void test() {

        Computer officeComputer = new OfficeComputer();
        Computer gamingComputer = new GamingComputer();
        Computer programmingComputer = new ProgrammingComputer();

        officeComputer.buildComputer();
        System.out.println(officeComputer.show());

        gamingComputer.buildComputer();
        System.out.println(gamingComputer.show());

        programmingComputer.buildComputer();
        System.out.println(programmingComputer.show());

    }



    public void asd(){
    }




}
