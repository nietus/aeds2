#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <string.h>

void trocaLetra(char* s, char first, char second) {
    for (int i = 0; s[i] != '\0'; i++) {
        if (s[i] == first) {
            s[i] = second;
        }
    }
}

int stringEq(char* testado, char* testando) {
    while (*testado && *testando) {
        if (*testado != *testando) {
            return 0;
        }
        testado++;
        testando++;
    }
    return (*testado == '\0' && *testando == '\0');
}

void swapAndPrint() {
    char *input = NULL;
    size_t buffer_size = 256;

    getline(&input, &buffer_size, stdin);

    if (input[strlen(input) - 1] == '\n') { // rm quebra de linha
        input[strlen(input) - 1] = '\0';
    }

    if (stringEq(input, "FIM")) {
        free(input);
        return;
    }

    char firstLetter = 'a' + rand() % 26;
    char secondLetter = 'a' + rand() % 26;

    trocaLetra(input, firstLetter, secondLetter);
    printf("%s\n", input);

    free(input);
    swapAndPrint(); // Recursivo
}

int main() {
    srand(4);

    swapAndPrint();

    return 0;
}