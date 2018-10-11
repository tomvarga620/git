////
//// Created by Klaudia Kriva on 10/10/2018.
////
//
//#include <stdio.h>
//#include <stdlib.h>
//#include <time.h>
//
//int main()
//{
//    srand(time(NULL));
//
//    int i, pocitadlo, max, min;
//    float aritpriemer;
//    pocitadlo=0;
//    int arr[30];
//
//    for (i=0; i<30; i++)
//    {
//        arr[i]=rand()%90+10;
//        printf("%d ", arr[i]);
//    }
//
//    printf("\n\nParne cisla su: \n");
//    for (i=0; i<30; i++)
//    {
//        if (arr[i]%2==0)
//            printf("%d ", arr[i]);
//    }
//
//    printf("\n\nCisla delitelne 3 a zaroven 5: \n");
//    for (i = 0; i < 30; i++)
//    {
//        if (arr[i]%5==0 && arr[i]%3==0)
//            printf("%d ", arr[i]);
//    }
//
//    printf("\n\nVypis cisla ktora maju cifru na mieste desiatok vyssiu nez na mieste jednotiek\n");
//    for (i=0; i<30; i++)
//    {
//        if (arr[i]%10 > arr[i]/10)
//            printf("%d ", arr[i]);
//    }
//
//    printf("\n\nciferny sucet je:\n");
//    for (i=0; i<30; i++)
//    {
//        if ((arr[i]%10 + arr[i]/10 ) == 10)
//            printf("%d ", arr[i]);
//    }
//
//    for (i = 0;  i<30 ; i++)
//    {
//        pocitadlo= pocitadlo + arr[i];
//    }
//
//    aritpriemer=(float)pocitadlo/30;
//    printf("\n\naritmeticky priemer cisel je: %1.2f \n", aritpriemer);
//
//    max=arr[0];
//    for (i=1; i<30; i++)
//    {
//        if (arr[i]>max)
//            max=arr[i];
//    }
//    printf("\n maximum je: %d", max);
//
//    min=arr[0];
//    for (i=1; i<30; i++)
//    {
//        if (arr[i]<min)
//            min=arr[i];
//    }
//    printf("\n minimum je: %d", min);
//
//
//}