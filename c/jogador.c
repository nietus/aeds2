#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>

#define MAX_PLAYERS 4000

int num_comp = 0;

typedef struct
{
    int id;
    char nome[100];
    int altura;
    int peso;
    char universidade[100];
    int anoNascimento;
    char cidadeNascimento[100];
    char estadoNascimento[100];
} Jogador;

void initJogador(Jogador *j)
{
    j->id = -1;
    strcpy(j->nome, "nao informado");
    j->altura = -1;
    j->peso = -1;
    strcpy(j->universidade, "nao informado");
    j->anoNascimento = -1;
    strcpy(j->cidadeNascimento, "nao informado");
    strcpy(j->estadoNascimento, "nao informado");
}

void removeSpecialCharacters(char *str)
{
    char *src = str;
    char *dst = str;

    while (*src)
    {
        if(*src == '\n'){
            *src = '\0';
        }
        if (*src != '"' && *src != ',' && *src != '*')
            *dst++ = *src;
        src++;
    }

    *dst = '\0';
}

int binarySearch(Jogador jogadores[], int num_players, char buffer[100])
{
    int left = 0, right = num_players - 1;
    while (left <= right)
    {
        int mid = left + (right - left) / 2;
        int cmp = strcmp(jogadores[mid].nome, buffer);
        if (cmp == 0){
            num_comp++;
            return mid;
        }
        
        else if (cmp < 0)
            left = mid + 1;
        else
            right = mid - 1;
    }

    return -1;
}

void insertionSort(Jogador arr[], int n)
{
    int i, j;
    Jogador key;
    for (i = 1; i < n; i++)
    {
        key = arr[i];
        j = i - 1;

        while (j >= 0 && strcmp(arr[j].nome, key.nome) > 0)
        {
            num_comp++;
            arr[j + 1] = arr[j];
            j = j - 1;
        }
        arr[j + 1] = key;
    }
}

void selectionSortRec(Jogador arr[], int n)
{
    
}

int main()
{
    char path[] = "../src/Players.csv";
    //char path[] = "/tmp/players.csv";
    Jogador jogadores[MAX_PLAYERS];
    int num_players = 0;

    FILE *file = fopen(path, "r");
    if (file == NULL)
    {
        perror("Error opening file");
        return EXIT_FAILURE;
    }

    char line[100];
    int jump_header = 1;

    while (fgets(line, sizeof(line), file))
    {
        if (jump_header)
        {
            jump_header = 0;
            continue;
        }

        Jogador j;
        initJogador(&j);

        char *token = strtok(line, ",");
        int count = 0;

        while (token != NULL)
        {
            removeSpecialCharacters(token);

            if (strcmp(token, "") == 0)
            {
                token = strtok(NULL, ",");
                count++;
                continue;
            }

            switch (count)
            {
            case 0:
                j.id = atoi(token);
                break;
            case 1:
                strcpy(j.nome, token);
                break;
            case 2:
                j.altura = atoi(token);
                break;
            case 3:
                j.peso = atoi(token);
                break;
            case 4:
                strcpy(j.universidade, token);
                break;
            case 5:
                j.anoNascimento = atoi(token);
                break;
            case 6:
                strcpy(j.cidadeNascimento, token);
                break;
            case 7:
                strcpy(j.estadoNascimento, token);
                break;
            }

            token = strtok(NULL, ",");
            count++;
        }
        jogadores[num_players++] = j;
    }

    // Process input
    Jogador k[200];
    int i = 0;
    char buffer[100];
    while (fgets(buffer, sizeof(buffer), stdin) != NULL)
    {
        size_t len = strlen(buffer);
        if (len > 0 && buffer[len - 1] == '\n')
        {
            buffer[len - 1] = '\0';
        }
        if (strcmp(buffer, "FIM") == 0)
        {
            break;
        }
        k[i] = jogadores[atoi(buffer)];
        i++;
    }

    double time_spent = 0.0;

    clock_t start = clock();

/*     insertionSort(k, i);

    while (fgets(buffer, sizeof(buffer), stdin) != NULL)
    {
        size_t len = strlen(buffer);
        if (len > 0 && buffer[len - 1] == '\n')
        {
            buffer[len - 1] = '\0';
        }
        if (strcmp(buffer, "FIM") == 0)
        {
            break;
        }

        int resp = binarySearch(k, i, buffer);

        if (resp != -1)
        {
            printf("SIM\n");
        }
        else
        {
            printf("NAO\n");
        }
    } */

    selectionSortRec(k, i);

    clock_t end = clock();

    time_spent += (double)(end - start) / CLOCKS_PER_SEC;

    FILE *logFile = fopen("1368777_binario.txt", "w");
    fprintf(logFile, "1368777 \t%f\t %d\n", time_spent, num_comp);
    fclose(logFile);
    fclose(file);

    return 0;
}