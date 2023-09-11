#include<stdio.h>
#include<string.h>
#include<stdlib.h>

int isPalindromo(char *string){
    for(int i = 0; i < strlen(string)/2;i++){
        if(string[i] != string[strlen(string) - i - 1]){
            return 0;
        }
    }
    return  1;
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
            if (isPalindromo(input)) {
                printf("SIM\n");
            } else {
                printf("NAO\n");
            }
        }
    }

    free(input);

    return 0;
}