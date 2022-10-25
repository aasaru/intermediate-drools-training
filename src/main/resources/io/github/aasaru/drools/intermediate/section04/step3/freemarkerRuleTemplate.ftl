rule "Verdict based on row ${ruleNumber}"
    salience 0
when
    $folder: VisaApplicationFolder(
        passport.age >= ${minAge},
        passport.age <= ${maxAge}
    )
    not VisaVerdict(applicationId == $folder.visaApplicationId)
then
    System.out.println("Issuing visa for #" + $folder.getVisaApplicationId());
    insert(VisaVerdict.issueVisa($folder.getVisaApplicationId()));
end

<#if unemployed!false>
rule "Flip coin ${ruleNumber}"
    salience 10
when
    $folder: VisaApplicationFolder(
        passport.age >= ${minAge},
        passport.age <= ${maxAge},
        booking == ${hasBooking?string}
    )
    not VisaVerdict(applicationId == $folder.visaApplicationId)
then
    java.util.Random random = new java.util.Random();
    int randomInt = random.ints(0, 100).findFirst().getAsInt();
    double threshold = ${percentageToInterview}*100;
    if (randomInt < threshold) {
        System.out.println("Calling #"+$folder.getVisaApplicationId()+" to interview as random "+randomInt+" fell below threshold "+threshold);
        insert(VisaVerdict.callToInterview($folder.getVisaApplicationId()));
    }
    else {
        System.out.println("Issuing visa for #"+$folder.getVisaApplicationId()+" as random "+randomInt+" went over threshold "+threshold);
        insert(VisaVerdict.issueVisa($folder.getVisaApplicationId()));
    }
end
</#if>