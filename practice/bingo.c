#include <stdio.h>

void quick(int arr[], int esq, int dir) {
        int i = esq;
        int j = dir;
        int pivo = arr[(esq+dir) / 2];
        while(i <= j){
            while(arr[i] < pivo){
                i++;
            }
            while(arr[j] > pivo){
                j--;
            }
            if(i <= j){
                int aux = arr[i];
                arr[i] = arr[j];
                arr[j] = aux;
                i++;
                j--;
            }
        }
        if(esq < j){
            quick(arr, esq, j);
        }
        if(i < dir){
            quick(arr, i, dir);
        }
    }

int main()
{
    int n, k, u;
    scanf("%d %d %d", &n, &k, &u);
    int arr[n][k + 1], numeros[u];

    for (int i = 0; i < n; i++)
    {
        arr[i][k] = 0;
        for (int j = 0; j < k; j++)
        {
            scanf("%d", &arr[i][j]);
        }
    }

    for (int j = 0; j < u; j++)
    {
        scanf("%d", &numeros[j]);
    }

    int first[n];

    for(int i = 0; i < n; i++){
        first[i] = 0;   
    }

    int stop = 0;

    for (int x = 0; x < u; x++)
    {
        for (int j = 0; j < k; j++)
        {
            for (int i = 0; i < n; i++)
            {
                //printf("Comparing %d to: %d [%d,%d]\n",numeros[x],arr[i][j],i,j);
                if (arr[i][j] == numeros[x])
                {
                    arr[i][k]++;
                    if(arr[i][k] == k){
                        first[i]++;
                        stop = 1;
                    }
                }
            }
        }
        if(stop == 1){
            break;
        }
    }

    for(int i = 0; i < n; i++){
        if(first[i] == 1){
            first[i] = i + 1;
        }
        else{
            first[i] = -1;
        }
    }

    quick(first, 0, n - 1);

     for(int i = 0; i < n; i++){
        if(first[i] != -1){
            printf("%d ", first[i]);
        }
    }

    return 0;
}