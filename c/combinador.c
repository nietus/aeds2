#include<stdio.h>
#include<string.h>
#include<stdlib.h>

int main(){
    char* s1;
    char* s2;
    int num = 1;
    while(num){
        s1 = (char*)calloc(100,sizeof(char));
        s2 = (char*)calloc(100,sizeof(char));
        scanf("%s %[^\n]", s1, s2);
        int tam = strlen(s1);
        if(strlen(s2) == 0 || strlen(s1) == 0)
            num--;
        if(strlen(s2) > strlen(s1))
            tam = strlen(s2);
        for(int j = 0; j < tam; j++){
            if(strlen(s1) > j)
                printf("%c",s1[j]);
            if(strlen(s2) > j)
                printf("%c",s2[j]);
            }
        free(s1);
        free(s2);
        printf("\n");
        }
    return 0;
}