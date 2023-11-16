#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>

#define INITIAL_MAX_PLAYERS 100
#define MAX_STRING_LENGTH 100
#define CAPACIDADE 5000
#define TAMANHO_MAX 5

int num_comp = 0;
int num_mov = 0;

typedef struct
{
    int id;
    char *nome;
    int altura;
    int peso;
    char *universidade;
    int anoNascimento;
    char *cidadeNascimento;
    char *estadoNascimento;
} Jogador;

void initJogador(Jogador *j)
{
    j->id = -1;
    j->nome = strdup("nao informado");
    j->altura = -1;
    j->peso = -1;
    j->universidade = strdup("nao informado");
    j->anoNascimento = -1;
    j->cidadeNascimento = strdup("nao informado");
    j->estadoNascimento = strdup("nao informado");
}

void freeJogador(Jogador *j)
{
    free(j->nome);
    free(j->universidade);
    free(j->cidadeNascimento);
    free(j->estadoNascimento);
}

void removeSpecialCharacters(char *str)
{
    char *src = str;
    char *dst = str;

    while (*src)
    {
        if (*src == '\n')
        {
            *src = '\0';
        }
        if (*src != '"' && *src != ',')
            *dst++ = *src;
        src++;
    }

    *dst = '\0';
}

void swap(Jogador *arr, int i, int j)
{
    Jogador temp = arr[i];
    arr[i] = arr[j];
    arr[j] = temp;
}

void printP(Jogador player)
{
    printf("[%d ## %s ## %d ## %d ## %d ## %s ## %s ## %s]\n",
           player.id, player.nome, player.altura, player.peso,
           player.anoNascimento, player.universidade,
           player.cidadeNascimento, player.estadoNascimento);
}

void printArrayP(Jogador *j, int n)
{
    for (int i = 0; i < n; i++)
    {
        printP(j[i]);
    }
}

int binarySearch(Jogador *jogadores, int num_players, char buffer[MAX_STRING_LENGTH])
{
    int left = 0, right = num_players - 1;
    while (left <= right)
    {
        num_comp++;
        int mid = left + (right - left) / 2;
        int cmp = strcmp(jogadores[mid].nome, buffer);
        if (cmp == 0)
        {
            num_comp++;
            return mid;
        }

        else if (cmp < 0)
        {
            left = mid + 1;
            num_comp += 2;
        }
        else
        {
            right = mid - 1;
            num_comp += 3;
        }
    }

    return -1;
}

void insertionSort(Jogador *arr, int n)
{
    int i, j;
    Jogador key;
    for (i = 1; i < n; i++)
    {
        key = arr[i];
        j = i - 1;

        while (j >= 0 && strcmp(arr[j].nome, key.nome) > 0)
        {
            arr[j + 1] = arr[j];
            num_comp++;
            j = j - 1;
        }
        arr[j + 1] = key;
    }
}

void selectionSortRec(Jogador *arr, int i, int n)
{
    if (i >= n - 1)
        return;

    int menor = i;
    for (int j = i + 1; j < n; j++)
    {
        if (strcmp(arr[j].nome, arr[menor].nome) < 0)
        {
            menor = j;
            num_comp++;
        }
    }

    Jogador tmp = arr[i];
    arr[i] = arr[menor];
    arr[menor] = tmp;
    num_mov += 3;

    selectionSortRec(arr, i + 1, n);
}

void colorInsertion(Jogador *arr, int cor, int h, int n) // For shellSort
{
    for (int i = h + cor; i < n; i++)
    {
        Jogador tmp = arr[i];
        int j = i - h;
        while (j >= 0 && ((arr[j].peso > tmp.peso) || (arr[j].peso == tmp.peso && strcmp(arr[j].nome, tmp.nome) > 0)))
        {
            num_comp++;
            arr[j + h] = arr[j];
            num_mov++;
            j -= h;
        }
        arr[j + h] = tmp;
        num_mov++;
    }
}

void shellSort(Jogador *arr, int n)
{
    int h = 1; // Space between each color's element;
    do
    {
        h = (h * 3) + 1; // Knuth's recommendation
    } while (h < n);
    do
    {
        h /= 3;
        for (int cor = 0; cor < h; cor++)
        {
            colorInsertion(arr, cor, h, n);
        }
    } while (h != 1);
}

void quickSort(Jogador *arr, int esq, int dir)
{
    int i = esq;
    int j = dir;
    Jogador pivo = arr[(esq + dir) / 2];
    num_mov++;

    while (i <= j)
    {
        while (strcmp(arr[i].estadoNascimento, pivo.estadoNascimento) < 0 ||
               (strcmp(arr[i].estadoNascimento, pivo.estadoNascimento) == 0 &&
                strcmp(arr[i].nome, pivo.nome) < 0))
        {
            i++;
            num_comp++;
        }

        while (strcmp(arr[j].estadoNascimento, pivo.estadoNascimento) > 0 ||
               (strcmp(arr[j].estadoNascimento, pivo.estadoNascimento) == 0 &&
                strcmp(arr[j].nome, pivo.nome) > 0))
        {
            j--;
            num_comp++;
        }

        if (i <= j)
        {
            Jogador tmp = arr[i];
            arr[i] = arr[j];
            arr[j] = tmp;
            num_mov += 3;
            i++;
            j--;
        }
    }

    if (esq < j)
    {
        quickSort(arr, esq, j);
    }

    if (i < dir)
    {
        quickSort(arr, i, dir);
    }
}

void bubble(Jogador *arr, int n)
{
    for (int i = n - 1; i > 0; i--)
    {
        for (int j = 0; j < i; j++)
        {
            if (arr[j].anoNascimento > arr[j + 1].anoNascimento || (arr[j].anoNascimento == arr[j + 1].anoNascimento && strcmp(arr[j].nome, arr[j + 1].nome) > 0))
            {
                num_comp++;
                Jogador tmp = arr[j];
                arr[j] = arr[j + 1];
                arr[j + 1] = tmp;
                num_mov += 3;
            }
        }
    }
}

int getMax(Jogador *arr, int n)
{
    int max = arr[0].id;
    for (int i = 1; i < n; i++)
    {
        if (arr[i].id > max)
            max = arr[i].id;
    }
    return max;
}

void countSort(Jogador *arr, int n, int exp)
{
    Jogador *output = (Jogador *)malloc(n * sizeof(Jogador));
    int i, count[10] = {0};

    // Store count of occurrences in count[]
    for (i = 0; i < n; i++)
        count[(arr[i].id / exp) % 10]++;

    // Change count[i] so that count[i] now contains actual position of this digit in output[]
    for (i = 1; i < 10; i++)
    {
        count[i] += count[i - 1];
        num_mov++;
    }

    // Build the output array
    for (i = n - 1; i >= 0; i--)
    {
        output[count[(arr[i].id / exp) % 10] - 1] = arr[i];
        count[(arr[i].id / exp) % 10]--;
        num_mov += 2;
    }

    for (i = 0; i < n; i++)
    {
        arr[i] = output[i];
        num_mov++;
    }

    free(output);
}

void radixsort(Jogador *arr, int n)
{
    int max = getMax(arr, n);
    for (int exp = 1; max / exp > 0; exp *= 10)
        countSort(arr, n, exp);
}

void partialInsertionSort(Jogador *arr, int size, int k)
{
    for (int i = 1; i < size; i++)
    {
        Jogador tmp = arr[i];
        int j = (i < k) ? i - 1 : k - 1;
        while (((j >= 0) && (arr[j].anoNascimento > tmp.anoNascimento)) || (j >= 0 && arr[j].anoNascimento == tmp.anoNascimento &&
                                                                            strcmp(arr[j].nome, tmp.nome) > 0))
        {
            arr[j + 1] = arr[j];
            j--;
        }
        arr[j + 1] = tmp;
    }
}

void partialHeapify(Jogador *arr, int n, int i)
{
    int largest = i;
    int left = 2 * i + 1;
    int right = 2 * i + 2;

    if (left < n && (arr[left].altura > arr[largest].altura || (arr[left].altura == arr[largest].altura && strcmp(arr[left].nome, arr[largest].nome) > 0)))
        largest = left;

    if (right < n && (arr[right].altura > arr[largest].altura || (arr[right].altura == arr[largest].altura && strcmp(arr[right].nome, arr[largest].nome) > 0)))
        largest = right;

    if (largest != i)
    {
        swap(arr, i, largest);
        partialHeapify(arr, n, largest);
    }
}

void partialHeapSort(Jogador *arr, int n, int k)
{
    // Build heap
    for (int i = k / 2 - 1; i >= 0; i--)
        partialHeapify(arr, k, i);

    // Heap sort
    for (int i = k; i < n; i++)
    {
        if (arr[i].altura < arr[0].altura || (arr[i].altura == arr[0].altura && strcmp(arr[i].nome, arr[0].nome) < 0))
        {
            swap(arr, i, 0);
            partialHeapify(arr, k, 0);
        }
    }

    for (int i = k - 1; i > 0; i--)
    {
        swap(arr, 0, i);
        partialHeapify(arr, i, 0);
    }
}

typedef struct
{
    Jogador lista[CAPACIDADE];
    int tam;
} ListaSequencial;

void initListaSequencial(ListaSequencial *l)
{
    l->tam = 0;
}

void inserirInicioListaSequencial(ListaSequencial *l, Jogador j)
{
    if (l->tam >= CAPACIDADE)
    {
        printf("Tamanho maximo atingido!\n");
    }
    else
    {
        for (int i = l->tam; i > 0; i--)
        {
            l->lista[i] = l->lista[i - 1];
        }
        l->lista[0] = j;
        l->tam++;
    }
}

void inserirFimListaSequencial(ListaSequencial *l, Jogador j)
{
    if (l->tam >= CAPACIDADE)
    {
        printf("Tamanho maximo atingido!\n");
    }
    else
    {
        l->lista[l->tam] = j;
        l->tam++;
    }
}

void inserirListaSequencial(ListaSequencial *l, Jogador j, int x)
{
    if (l->tam >= CAPACIDADE || x >= CAPACIDADE)
    {
        printf("Tamanho maximo atingido!\n");
    }
    else
    {
        for (int i = l->tam; i >= x; i--)
        {
            l->lista[i] = l->lista[i - 1];
        }
        l->lista[x] = j;
        l->tam++;
    }
}


void removerInicioListaSequencial(ListaSequencial *l)
{
    if (l->tam <= 0)
    {
        printf("Empty!\n");
    }
    else
    {
        Jogador removed = l->lista[0];
        printf("(R) %s\n",removed.nome);
        for (int i = 0; i < l->tam - 1; i++)
        {
            l->lista[i] = l->lista[i + 1];
        }
        l->tam--;
    }
}

void removerFimListaSequencial(ListaSequencial *l)
{
    if (l->tam <= 0)
    {
        printf("Empty!\n");
    }
    else
    {
        int tam = l->tam;
        Jogador removed = l->lista[tam - 1];
        printf("(R) %s\n",removed.nome);
        l->tam--;
    }
}

void removerListaSequencial(ListaSequencial *l, int x)
{
    if (l->tam <= 0 || x >= CAPACIDADE)
    {
        printf("Empty!\n");
    }
    else
    {
        Jogador removed = l->lista[x];
        printf("(R) %s\n",removed.nome);
        for (int i = x; i < l->tam - 1; i++)
        {
            l->lista[i] = l->lista[i + 1];
        }
        l->tam--;
    }
}

typedef struct Celula {
    Jogador j;
    struct Celula *prox;
} Celula;

typedef struct PilhaFlexivel {
    Celula *topo;
    int tamanho;
} PilhaFlexivel;

void initCelula(Celula *c, Jogador jogador) {
    c->j = jogador;
    c->prox = NULL;
}

void initPilhaFlexivel(PilhaFlexivel *p) {
    p->topo = NULL;
    p->tamanho = 0;
}

int getTamanho(PilhaFlexivel *p) {
    return p->tamanho;
}

Jogador getJogador(PilhaFlexivel *p, int x) {
    if (x < 0 || x >= p->tamanho) {
        exit(EXIT_FAILURE);
    }

    Celula *atual = p->topo;
    for (int i = 0; i < x; i++) {
        atual = atual->prox;
    }

    return atual->j;
}

Celula *getTopo(PilhaFlexivel *p) {
    if (p->topo == NULL) {
        exit(EXIT_FAILURE);
    }
    return p->topo;
}

Jogador getJogadorTopo(PilhaFlexivel *p) {
    if (p->topo == NULL) {
        exit(EXIT_FAILURE);
    }
    return p->topo->j;
}

void empilhar(PilhaFlexivel *p, Jogador j) {
    Celula *novaCelula = (Celula *)malloc(sizeof(Celula));
    if (novaCelula == NULL) {
        exit(EXIT_FAILURE);
    }

    novaCelula->j = j;
    novaCelula->prox = p->topo;
    p->topo = novaCelula;
    p->tamanho++;
}

Jogador desempilhar(PilhaFlexivel *p) {
    if (p->topo == NULL) {
        exit(EXIT_FAILURE);
    }

    Jogador removido = p->topo->j;
    Celula *tmp = p->topo;
    p->topo = p->topo->prox;
    tmp->prox = NULL;
    free(tmp);
    p->tamanho--;

    return removido;
}

void printCustom(Jogador player, int i)
{
    printf("[%d] ## %s ## %d ## %d ## %d ## %s ## %s ## %s ##\n",
           i, player.nome, player.altura, player.peso,
           player.anoNascimento, player.universidade,
           player.cidadeNascimento, player.estadoNascimento);
}

void printArrayCustom(ListaSequencial *l, int n, int i)
{
    for (int j = 0; j < n; j++)
    {
        printCustom(l->lista[j], i++);
    }
}


void printArrayCustomPilhaRec(Celula *c, int *i) {
    if (c != NULL) {
        printArrayCustomPilhaRec(c->prox, i);
        printCustom(c->j, (*i)++);
    }
}

void printArrayCustomPilha(PilhaFlexivel *p, int n, int i) {
    printArrayCustomPilhaRec(p->topo, &i);
}


Jogador jogadorById(Jogador* jogadores, int n, int x){
    for (int j = 0; j < n; j++)
    {
        if(jogadores[j].id == x){
            return jogadores[j];
        }
    }
    return jogadores[0];
}


typedef struct {
    Celula *inicio;
    int size; 
    double alturaMedia; 
} FilaFlexivel;

void atualizarMedia(FilaFlexivel *l) {
    double alturaTotal = 0.0;
    Celula *atual = l->inicio;

    while (atual != NULL) {
        alturaTotal += atual->j.altura;
        atual = atual->prox;
    }

    l->alturaMedia = l->size > 0 ? alturaTotal / l->size : 0.0;
    printf("%.0f\n", l->alturaMedia);
}

void initFilaFlexivel(FilaFlexivel *l) {
    l->inicio = NULL;
    l->size = 0;
    l->alturaMedia = 0.0;
}

void removerPrimeiro(FilaFlexivel *ff) {
    if (ff->inicio == NULL) {
        printf("Empty!\n");
        return;
    }

    Celula *removida = ff->inicio;
    ff->inicio = ff->inicio->prox;
    free(removida);

    ff->size--;
}

void inserirFimFilaFlexivel(FilaFlexivel *l, Jogador j) {
    if (l->size == 5) {
        removerPrimeiro(l);
    }

    Celula *novaCelula = (Celula *)malloc(sizeof(Celula));
    if (novaCelula == NULL) {
        exit(EXIT_FAILURE);
    }

    novaCelula->j = j;
    novaCelula->prox = NULL;

    if (l->inicio == NULL) {
        l->inicio = novaCelula;
    } else {
        Celula *atual = l->inicio;
        while (atual->prox != NULL) {
            atual = atual->prox;
        }
        atual->prox = novaCelula;
    }

    l->size++;
    atualizarMedia(l);
}

void removerInicioFilaFlexivel(FilaFlexivel *l) {
    if (l->inicio == NULL) {
        printf("Empty!\n");
        return;
    }
    Celula *removida = l->inicio;
    printf("(R) %s\n",removida->j.nome);
    l->inicio = l->inicio->prox;
    free(removida);

    l->size--;
    atualizarMedia(l);
}

void printListaCustom(FilaFlexivel *l, int i) {
    Celula *atual = l->inicio;

    while (atual != NULL) {
        // Assuming printCustom is a function to print Jogador
        printCustom(atual->j, i++);
        atual = atual->prox;
    }
}

void printJogador(Jogador j) {
    printf("[%d ## %s ## %d ## %d ## %d ## %s ## %s ## %s]\n",
           j.id, j.nome, j.altura, j.peso,
           j.anoNascimento, j.universidade,
           j.cidadeNascimento, j.estadoNascimento);
}

void printFilaFlexivel(FilaFlexivel *l) {
    Celula *atual = l->inicio;
    while (atual != NULL) {
        printJogador(atual->j);
        atual = atual->prox;
    }
}

typedef struct {
    Jogador fila[TAMANHO_MAX];
    int front;  // Points to the front of the queue
    int rear;   // Points to the next available position
    int size;   // Current number of elements in the queue
} FilaLinear;

void atualizarMediaLinear(Jogador fila[], int size) {
    double alturaTotal = 0.0;

    for (int i = 0; i < size; i++) {
        alturaTotal += fila[i].altura;
    }

    double alturaMedia = size > 0 ? alturaTotal / size : 0.0;
    printf("%.0f\n", alturaMedia);
}

void inicializarFilaLinear(FilaLinear *fila) {
    fila->front = 0;
    fila->rear = 0;
    fila->size = 0;
}

void removerPrimeiroFilaLinear(FilaLinear *fila) {
    if (fila->size == 0) {
        printf("Empty!\n");
        return;
    }

    // Remove the first element
    printf("(R) %s\n", fila->fila[fila->front].nome);
    fila->front = (fila->front + 1) % TAMANHO_MAX;
    fila->size--;

    // Update the mean
    atualizarMediaLinear(fila->fila, fila->size);
}

void inserirFilaLinear(FilaLinear *fila, Jogador j) {
    if (fila->size == TAMANHO_MAX) {
        // If the queue is full, remove the first element
        fila->front = (fila->front + 1) % TAMANHO_MAX;
        fila->size--;
    }

    // Add the new element
    fila->fila[fila->rear] = j;
    fila->rear = (fila->rear + 1) % TAMANHO_MAX;
    fila->size++;

    // Update the mean
    atualizarMediaLinear(fila->fila, fila->size);
}

void printFilaLinear(FilaLinear *fila) {
    int i = fila->front;
    int count = 0;

    while (count < fila->size) {
        printf("[%s ## %d]\n", fila->fila[i].nome, fila->fila[i].altura);
        i = (i + 1) % TAMANHO_MAX;
        count++;
    }
}

int main()
{
    //char path[] = "../src/Players.csv";
    char path[] = "/tmp/players.csv";

    FILE *file = fopen(path, "r");
    if (file == NULL)
    {
        perror("Error opening file");
        return EXIT_FAILURE;
    }

    char line[MAX_STRING_LENGTH];
    int jump_header = 1;
    int num_players = 0;
    int max_players = INITIAL_MAX_PLAYERS;
    Jogador *jogadores = malloc(max_players * sizeof(Jogador));

    while (fgets(line, sizeof(line), file))
    {
        if (jump_header == 1)
        {
            jump_header = 0;
            continue;
        }
        if (num_players >= max_players)
        {
            max_players *= 2;
            jogadores = realloc(jogadores, max_players * sizeof(Jogador));
            if (jogadores == NULL)
            {
                fprintf(stderr, "Memory allocation failed.\n");
                fclose(file);
                return EXIT_FAILURE;
            }
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
                j.nome = strdup(token);
                break;
            case 2:
                j.altura = atoi(token);
                break;
            case 3:
                j.peso = atoi(token);
                break;
            case 4:
                j.universidade = strdup(token);
                break;
            case 5:
                j.anoNascimento = atoi(token);
                break;
            case 6:
                j.cidadeNascimento = strdup(token);
                break;
            case 7:
                j.estadoNascimento = strdup(token);
                break;
            }

            token = strtok(NULL, ",");
            count++;
        }
        jogadores[num_players++] = j;
    }

    FilaLinear fl;
    inicializarFilaLinear(&fl);

    char buffer[MAX_STRING_LENGTH];
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
        for (int b = 0; b < num_players; b++)
        {
            if (jogadores[b].id == atoi(buffer) ) // && jogadores[b].anoNascimento > 100) // && for bubble and partial insertion to work, due to bad formatation of the csv
            {
                inserirFilaLinear(&fl,jogadores[b]);
                break;
            }
        }
    }

    int size;
    scanf("%d", &size);

    char string[1024];
    char command[3];
    int pos;
    int id;

    for(int i = 0; i < size; i++) {
        scanf("%s",command);
         if(strcmp(command, "I") == 0)
        {
            scanf("%d",&id);
            inserirFilaLinear(&fl, jogadorById(jogadores, max_players, id));
        }
        else if (strcmp(command, "R") == 0)
        {
            removerPrimeiroFilaLinear(&fl);
        }
        
    }


    // PilhaFlexivel pilha;
    // initPilhaFlexivel(&pilha);

    // char buffer[MAX_STRING_LENGTH];
    // while (fgets(buffer, sizeof(buffer), stdin) != NULL)
    // {
    //     size_t len = strlen(buffer);
    //     if (len > 0 && buffer[len - 1] == '\n')
    //     {
    //         buffer[len - 1] = '\0';
    //     }
    //     if (strcmp(buffer, "FIM") == 0)
    //     {
    //         break;
    //     }
    //     for (int b = 0; b < num_players; b++)
    //     {
    //         if (jogadores[b].id == atoi(buffer) ) // && jogadores[b].anoNascimento > 100) // && for bubble and partial insertion to work, due to bad formatation of the csv
    //         {
    //             empilhar(&pilha,jogadores[b]);
    //             break;
    //         }
    //     }
    // }

    // int size;
    // scanf("%d", &size);

    // char string[1024];
    // char command[3];
    // int pos;
    // int id;

    // for(int i = 0; i < size; i++) {
    //     scanf("%s",command);
    //      if(strcmp(command, "I") == 0)
    //     {
    //         scanf("%d",&id);
    //         empilhar(&pilha,jogadorById(jogadores,max_players,id));
    //     }
    //     else if (strcmp(command, "R") == 0)
    //     {
    //         Jogador j = desempilhar(&pilha);
    //         printf("(R) %s\n",j.nome);
    //     }
    // }

    // printArrayCustomPilha(&pilha, pilha.tamanho, 0);

    // int size;
    // scanf("%d", &size);

    // char string[1024];
    // char command[3];
    // int pos;
    // int id;

    // for(int i = 0; i < size; i++) {
    //     scanf("%s",command);
    //      if(strcmp(command, "II") == 0)
    //     {
    //         scanf("%d",&id);
    //         inserirInicioListaSequencial(&l, jogadorById(jogadores, max_players, id));
    //     }
    //     else if (strcmp(command, "I*") == 0)
    //     {
    //         scanf("%d %d",&pos, &id);
    //         inserirListaSequencial(&l, jogadorById(jogadores, max_players, id), pos);
    //         //printf("Inserido em %d: %d\n",pos, id);
    //     }
    //     else if (strcmp(command, "IF") == 0)
    //     {
    //         scanf("%d",&id);
    //         inserirFimListaSequencial(&l,jogadorById(jogadores, num_players, id));
    //     }
    //     else if (strcmp(command, "RI") == 0)
    //     {
    //         removerInicioListaSequencial(&l);
    //     }
    //     else if (strcmp(command, "R*") == 0)
    //     {
    //         scanf("%d",&id);
    //         removerListaSequencial(&l, id);
    //     }
    //     else if (strcmp(command, "RF") == 0)
    //     {
    //         removerFimListaSequencial(&l);
    //     }
    // }

    // printArrayCustom(&l, l.tam, 0);

    // selectionSortRec(k, 0, i);

    // shellSort(k,i);

    // quickSort(k, 0, i - 1);

    // bubble(k, i);

    // radixsort(k, i);

    // partialInsertionSort(k, i, 10); // 10 is the partial variable for the exercise

    // partialHeapSort(k,i,10);

    // printArrayP(k, 10);

    // Free
    for (int idx = 0; idx < num_players; idx++)
    {
        freeJogador(&jogadores[idx]);
    }
    free(jogadores);
    // free(k);

    fclose(file);

    return 0;
}