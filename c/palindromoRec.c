#include<stdio.h>
#include<string.h>
#include<stdlib.h>

int isPalindromo(char *string, int comeco, int fim){
    if(comeco >= fim){
        return 1;
    }
    if(string[comeco] != string[fim - 1])
        return 0;
    return(isPalindromo(string,comeco + 1, fim - 1));
}

int main() {
    char *input = NULL;
    size_t bufsize = 0;
    int characters;
    int n = 0;

    while (n == 0) {
        characters = getline(&input, &bufsize, stdin);

        input[characters - 1] = '\0';  // Remove the newline character

        if (strcmp(input, "FIM") == 0) {
            n = 1;
            getchar();
        }
        if(n==0){
            if (isPalindromo(input, 0, strlen(input))) { // Start at 0, ends at size of string
                printf("SIM\n");
            } else {
                printf("NAO\n");
            }
        }
    }

    free(input);

    return 0;
}