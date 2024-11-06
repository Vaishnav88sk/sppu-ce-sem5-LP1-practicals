// MathOperations.cpp : Defines the exported functions for the DLL.
#include "pch.h"  // for precompiled header (if using Visual Studio)
#include "MathOperations.h"

#define DLL_EXPORT __declspec(dllexport)

extern "C" {

    // Function for addition
    DLL_EXPORT double add(double a, double b) {
        return a + b;
    }

    // Function for subtraction
    DLL_EXPORT double subtract(double a, double b) {
        return a - b;
    }

    // Function for multiplication
    DLL_EXPORT double multiply(double a, double b) {
        return a * b;
    }

    // Function for division
    DLL_EXPORT double divide(double a, double b) {
        if (b == 0) {
            return 0;  // Handle divide by zero error
        }
        return a / b;
    }
}
