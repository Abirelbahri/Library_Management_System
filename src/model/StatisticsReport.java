package model;

public class StatisticsReport {
    private String reportName;
    private String description;
    private int value;

    public StatisticsReport(String reportName, String description, int value) {
        this.reportName = reportName;
        this.description = description;
        this.value = value;
    }

    public String getReportName() {
        return reportName;
    }

    public void setReportName(String reportName) {
        this.reportName = reportName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
