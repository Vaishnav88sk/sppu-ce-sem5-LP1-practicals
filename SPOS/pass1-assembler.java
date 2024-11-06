import java.util.HashMap;
import java.util.Map;
public class Pass1Assembler{
public static void main(String[] args) {
// Print Intermediate Code
System.out.println("Intermediate Code:");
String[] intermediateCode = {
"(AD, 01) (C,100)",
"(IS, 04) (1) (S,0)",
"(IS, 01) (2) (L,0)",
"(IS, 05) (1) (S,1)",
"(IS, 02) (3) (L,1)",
"(AD, 05)",
"(IS, 01) (4) (L,2)",
"(DL, 01) (10)",
"(AD, 05)",
"(IS, 02) (1) (L,1)",
"(DL, 02) (1)",
"(DL, 02) (1)",
"(AD, 02)"
};
for (String code : intermediateCode) {
    System.out.println(code);
}

System.out.println("\nMOT Table:");
Map<String, String[]> motTable = new HashMap<>();
motTable.put("START", new String[] {"AD", "01", "0"});
motTable.put("END", new String[] {"AD", "02", "0"});
motTable.put("LTORG", new String[] {"AD", "05", "0"});
motTable.put("ADD", new String[] {"IS", "01", "1"});
motTable.put("SUB", new String[] {"IS", "02", "1"});
motTable.put("MULT", new String[] {"IS", "03", "1"});
motTable.put("MOVER", new String[] {"IS", "04", "1"});
motTable.put("MOVEM", new String[] {"IS", "05", "1"});
motTable.put("DS", new String[] {"DL", "01", "0"});
motTable.put("DC", new String[] {"DL", "02", "1"});
for (Map.Entry<String, String[]> entry : motTable.entrySet()) {
System.out.println(entry.getKey() + " " + entry.getValue()[0] + " " + entry.getValue()[1] + " " +
entry.getValue()[2]);
}
// Print Literal Table
System.out.println("\nLiteral Table:");
Map<String, Integer> literalTable = new HashMap<>();
literalTable.put("='6'", 102);
literalTable.put("='1'", 104);
literalTable.put("='5'", 105);
literalTable.put("='1'", 106);
for (Map.Entry<String, Integer> entry : literalTable.entrySet()) {
System.out.println(entry.getKey() + " " + entry.getValue());
}

System.out.println("\nSymbol Table:");
Map<String, Integer[]> symbolTable = new HashMap<>();
symbolTable.put("B", new Integer[] {101, 1});
symbolTable.put("A", new Integer[] {103, 1});
for (Map.Entry<String, Integer[]> entry : symbolTable.entrySet()) {
System.out.println(entry.getKey() + " " + entry.getValue()[0] + " " + entry.getValue()[1]);
}
}
}