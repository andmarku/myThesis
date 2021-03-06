package Settings;

import java.util.List;

public interface Settings {
    String getRbClusterPath();
    String getRbSamplingPath();
    String getRbOverlapPath();
    String getQueryPath();
    int getNumOfItr();
    int getSizeOfQuery();
    int getSizeOfRetrievedList();
    int getMaxSizeOfFinalList();
    int getMaxSizeToExpandToV1();
    void setStandardSettings();
    void setBetaAsInterpolation();
    void setGammaAsInterpolation();
    void changeNumberOfQueryTerms(int newNrOfQueries);
    List<List<Double>> getParamCombs();
    List<Double> getValuesOfP();
    List<Double> getValuesOfInnerP();
    List<Integer> getQuerySpecificityToTest();
}
