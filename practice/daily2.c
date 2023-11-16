#include<stdio.h>
#include<stdlib.h>
#include<string.h>

// Given an array of integers, return a new array such that each element at index i of the new array is the product of all the numbers in the original array except the one at i.

// For example, if our input was [1, 2, 3, 4, 5], the expected output would be [120, 60, 40, 30, 24]. If our input was [3, 2, 1], the expected output would be [2, 3, 6].

// Follow-up: what if you can't use division?

void main() {
    int arr[] = {1, 2, 3, 4, 5};
    int *aux = (int*)malloc(sizeof(arr) * sizeof(int));

    for(int i = 0; i < sizeof(arr) / sizeof(arr[0]); i++) {
        aux[i] = 1;
    }

    for(int i = 0; i < sizeof(arr) / sizeof(arr[0]); i++) {
        for(int j = 0; j < sizeof(arr) / sizeof(arr[0]); j++) {
            if(i == j) {
                continue;
            } else {
                aux[i] *= arr[j];
            }
        }
        printf("%d ", aux[i]);
    }

    free(aux);
}
