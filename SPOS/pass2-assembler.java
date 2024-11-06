import java.util.*;
class Symbol {
 String name;
 int address;
 Symbol(String name, int address) {
 this.name = name;
 this.address = address;
 }
}
public class TwoPassAssemblerPass2 {
 private static final List<Symbol> symbolTable = Arrays.asList(
 new Symbol("START", 0),
 new Symbol("A", 1),
 new Symbol("B", 2),
 new Symbol("END", 3)
 );
 private static final Map<String, String> opcodeMap = Map.of(
 "LOAD", "0001", // 01 in hex
 "STORE", "0010", // 02 in hex
 "ADD", "0011", // 03 in hex
 "SUB", "0100", // 04 in hex
 "JUMP", "0101" // 05 in hex
 );
 public static void main(String[] args) {
 String[] instructions = {
 "LOAD A",
 "ADD B",
 "STORE A",
 "JUMP START"
 };
 System.out.println("Machine Code:");
 for (String instruction : instructions) {
 generateMachineCode(instruction);
 }
 }
 private static void generateMachineCode(String instruction) {
     String[] parts = instruction.split(" ");
 String opcode = opcodeMap.get(parts[0]); // Get the opcode in binary
 int address = getAddress(parts[1]); // Get the address from the symbol table
 String machineCode = opcode + String.format("%04d",
Integer.parseInt(Integer.toBinaryString(address))); // Concatenate opcode and address
 System.out.println(machineCode); // Print the machine code
 }
 private static int getAddress(String symbol) {
 for (Symbol s : symbolTable) {
 if (s.name.equals(symbol)) {
 return s.address;
 }
 }
 return -1; // Not found
 }
}