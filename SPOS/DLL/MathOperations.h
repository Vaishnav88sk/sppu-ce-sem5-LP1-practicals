#pragma once

// Define the export function
extern "C" {
    __declspec(dllexport) double add(double a, double b);
    __declspec(dllexport) double subtract(double a, double b);
    __declspec(dllexport) double multiply(double a, double b);
    __declspec(dllexport) double divide(double a, double b);
}
