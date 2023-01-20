package fuzzy;


import net.sourceforge.jFuzzyLogic.FIS;
import net.sourceforge.jFuzzyLogic.plot.JFuzzyChart;
import net.sourceforge.jFuzzyLogic.rule.Rule;
import net.sourceforge.jFuzzyLogic.rule.Variable;


public class Main {
    public static void main(String[] args) throws Exception {
        String fileName = "fcl/file.fcl";
        FIS fis = FIS.load(fileName,true);

        if( fis == null ) {
            System.err.println("Can't load file: '" + fileName + "'");
            return;
        }

        // Change the variables, based on parameters fuzzy logic will decide the sport
        fis.setVariable("activity", 3);     // 0 - 4 (hours)
        fis.setVariable("teammates", 5);    // 0 - 11
        fis.setVariable("fear", 4);         // 0 - 11
        fis.setVariable("season", 2);       // 0 - 4
        fis.setVariable("cost", 800);       // 0 - 10000 (euro)

        fis.evaluate();
        Variable decision = fis.getVariable("decision");
        JFuzzyChart.get().chart(decision, decision.getDefuzzifier(), true);
        System.out.println(fis.getVariable("decision"));


        for( Rule r : fis.getFunctionBlock("tipper").getFuzzyRuleBlock("sport_rules").getRules()) {
            System.out.println(r);
        }
    }
}