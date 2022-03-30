/*
 * This program calculates the total saved by
 *  subscribing to a magazine instead of buying them individually.
 * Programmer: Zachary Wellman
 * Date of last modification: 3/8/2020
*/

#include <iostream>
#include <iomanip>
using namespace std;

float calcTotalSaved(float, float, int);
string message(float);

int main() {
    float newsStandPrice, subPrice, costOfIssueSub, totalSaved, percentSaved, avgAmountSaved = 0, largestAmount = -1;
    int numIssues, numNewspapers = 0;

    do {
        cout << "Enter Newsstand Price (Enter -9.99 to end the program): $";
        cin >> newsStandPrice;
    } while (newsStandPrice != (float)-9.99 && newsStandPrice <= 0);

    while (newsStandPrice != (float)-9.99) {
        do {
            cout << "Enter Subscription Price: $";
            cin >> subPrice;
        } while (subPrice <= 0);

        do {
            cout << "Enter Number of Issues in Subscription: ";
            cin >> numIssues;
        } while (numIssues <= 0);

        costOfIssueSub = subPrice/numIssues;
        totalSaved = calcTotalSaved(newsStandPrice, costOfIssueSub, numIssues);

        if (totalSaved > newsStandPrice*numIssues) {
            cout << "The subscription price is larger than the newsstand price for the number of issues." << endl;
        }

        percentSaved = (totalSaved/(newsStandPrice*numIssues))*100;

        cout << endl << fixed << showpoint << setprecision(2) << "Cost per Issue at the Subscription Rate: $" << costOfIssueSub <<endl;
        cout << "Percent Discount: " << percentSaved << "%" << endl;
        cout << "Total amount saved: $" << totalSaved << endl;
        cout << "********** " << message(percentSaved) << " **********" << endl << endl;

        numNewspapers++;
        avgAmountSaved += totalSaved;

        if (totalSaved > largestAmount) {
            largestAmount = totalSaved;
        }

        do {
        cout << "Enter Newsstand Price (Enter -9.99 to end the program): $";
        cin >> newsStandPrice;
        } while (newsStandPrice != (float)-9.99 && newsStandPrice <= 0);
    }

    avgAmountSaved /= numNewspapers;
    cout << "Total number of magazines processed: " << numNewspapers << endl;
    cout << "Average amount saved: " << avgAmountSaved << endl;
    cout << "Largest amount saved: " << largestAmount << endl;

    return 0;
}

float calcTotalSaved(float newsStand, float subscriptionPrice, int issues) {
    return (newsStand - subscriptionPrice) * issues;
}

string message(float percent) {
    string str = "";
    if (percent >= 75.0)
        str = "Bargain of the week!";
    else if (percent >= 50)
        str = "Great Deal!";
    else if (percent >= 25)
        str = "Special Offer!";
    else
        str = "Thank for your purchase!";

    return str;
}
