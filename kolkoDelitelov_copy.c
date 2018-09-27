////
//// Created by Klaudia Kriva on 26/09/2018.
////
//
//#include <stdio.h>
//
//int main()
//{
//    int number, delitel, vypocet, pocetdelitelov;
//    pocetdelitelov=0;
//
//
//    printf("Enter the number: ");
//    scanf("%d", &number);
//
////    for (delitel=1; delitel<=number; delitel++)
////    {
////        if (number%delitel==0)
////        {
////            pocetdelitelov+=1;
////            printf("%d\n", delitel);
////        }
////    }
////iny sposob je:
//
//    delitel=1;
//
//    while (delitel<=number)
//    {
//        vypocet=number%delitel;
//        if (vypocet==0) {
//            pocetdelitelov++;
//        }
//        delitel++;
//    }
//
//    printf("pocet delitelov je: %d\n", pocetdelitelov);
//
//    if (pocetdelitelov==2)
//        printf("prvocislo");
//    else
//        printf("zlozene cislo");
//}