#include <iostream>
#include <windows.h>  // for loading DLL functions

typedef double(*MathOperation)(double, double);  // Function pointer for operations

int main() {
    // Load the DLL
    HMODULE hDll = LoadLibrary(L"MathOperations.dll");

    if (hDll == NULL) {
        std::cerr << "Failed to load the DLL!" << std::endl;
        return -1;
    }

    // Get the function pointers for the operations
    MathOperation add = (MathOperation)GetProcAddress(hDll, "add");
    MathOperation subtract = (MathOperation)GetProcAddress(hDll, "subtract");
    MathOperation multiply = (MathOperation)GetProcAddress(hDll, "multiply");
    MathOperation divide = (MathOperation)GetProcAddress(hDll, "divide");

    if (!add || !subtract || !multiply || !divide) {
        std::cerr << "Failed to locate functions in the DLL!" << std::endl;
        return -1;
    }

    // Test the functions
    double a = 10, b = 5;

    std::cout << "Addition: " << add(a, b) << std::endl;
    std::cout << "Subtraction: " << subtract(a, b) << std::endl;
    std::cout << "Multiplication: " << multiply(a, b) << std::endl;
    std::cout << "Division: " << divide(a, b) << std::endl;

    // Free the DLL after usage
    FreeLibrary(hDll);

    return 0;
}
