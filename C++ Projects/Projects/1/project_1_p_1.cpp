/*
 * This program calculates the total saved by
 *  subscribing to a magazine instead of buying them individually.
 * Programmer: Zachary Wellman
 * Date of last modification: 2/20/2020
*/

#include <iostream>
#include <iomanip>
using namespace std;

float calcTotalSaved(float, float, int);
string message(float);

int main() {
    float newsStandPrice, subPrice, costOfIssueSub, totalSaved, percentSaved;
    int numIssues;

    cout << "Enter Newsstand Price: $";
    cin >> newsStandPrice;

    cout << "Enter Subscription Price: $";
    cin >> subPrice;

    cout << "Enter Number of Issues in Subscription: ";
    cin >> numIssues;

    costOfIssueSub = subPrice/numIssues;
    totalSaved = calcTotalSaved(newsStandPrice, costOfIssueSub, numIssues);
    percentSaved = (totalSaved/(newsStandPrice*numIssues))*100;

    cout << endl << fixed << showpoint << setprecision(2) << "Cost per Issue at the Subscription Rate: $" << costOfIssueSub <<endl;
    cout << "Percent Discount: " << percentSaved << "%" << endl;
    cout << "Total amount saved: $" << totalSaved << endl;
    cout << "********** " << message(percentSaved) << " **********";

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
