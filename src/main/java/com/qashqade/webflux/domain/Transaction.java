package com.qashqade.webflux.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@NoArgsConstructor
@Table(name = "transaction")
public class Transaction {

    @Id
    private Long id;
    @Column(value = "trx")
    String trx;
    @Column(value = "subTransactions")
    String subTransactions;
    @Column(value = "appliedDateChoice")
    String appliedDateChoice;
    @Column(value = "isRealized")
    boolean isRealized;
    @Column(value = "fxRateSource")
    String fxRateSource;
    @Column(value = "targetCcy")
    String targetCcy;
    @Column(value = "fxTable")
    String fxTable;
    @Column(value = "fx")
    String fx;
    @Column(value = "deals")
    String deals;
    @Column(value = "dealGroups")
    String dealGroups;
    @Column(value = "vintageYearNames")
    String vintageYearNames;
    @Column(value = "lps")
    String lps;
    @Column(value = "lpgroups")
    String lpgroups;
    @Column(value = "splitTypes")
    String splitTypes;
    @Column(value = "allDeals")
    String allDeals;
    @Column(value = "amount")
    String amount;
    @Column(value = "originalAmount")
    String originalAmount;
    @Column(value = "appliedDate")
    String appliedDate;
    @Column(value = "allLps")
    String allLps;
    @Column(value = "impactName")
    String impactName;
    @Column(value = "suid")
    String uid;
    @Column(value = "ccy")
    String ccy;
    @Column(value = "gpid")
    String gpid;
    @Column(value = "gahid")
    String gahid;
    @Column(value = "dealAllocationRule")
    String dealAllocationRule;
    @Column(value = "lpAllocationRule")
    String lpAllocationRule;
    @Column(value = "customfields")
    String customfields;
    @Column(value = "needsToSetProgressiveValues")
    boolean needsToSetProgressiveValues = false;
    @Column(value = "isPartOfAllProgressiveRuns")
    boolean isPartOfAllProgressiveRuns = false;

    public Transaction(
        String trx,
        String subTransactions,
        String appliedDateChoice,
        boolean isRealized,
        String fxRateSource,
        String targetCcy,
        String fxTable,
        String fx,
        String deals,
        String dealGroups,
        String vintageYearNames,
        String lps,
        String lpgroups,
        String splitTypes,
        String allDeals,
        String amount,
        String originalAmount,
        String appliedDate,
        String allLps,
        String impactName,
        String uid,
        String ccy,
        String gpid,
        String gahid,
        String dealAllocationRule,
        String lpAllocationRule,
        String customfields,
        boolean needsToSetProgressiveValues,
        boolean isPartOfAllProgressiveRuns
    ) {
        this.trx = trx;
        this.subTransactions = subTransactions;
        this.appliedDateChoice = appliedDateChoice;
        this.isRealized = isRealized;
        this.fxRateSource = fxRateSource;
        this.targetCcy = targetCcy;
        this.fxTable = fxTable;
        this.fx = fx;
        this.deals = deals;
        this.dealGroups = dealGroups;
        this.vintageYearNames = vintageYearNames;
        this.lps = lps;
        this.lpgroups = lpgroups;
        this.splitTypes = splitTypes;
        this.allDeals = allDeals;
        this.amount = amount;
        this.originalAmount = originalAmount;
        this.appliedDate = appliedDate;
        this.allLps = allLps;
        this.impactName = impactName;
        this.uid = uid;
        this.ccy = ccy;
        this.gpid = gpid;
        this.gahid = gahid;
        this.dealAllocationRule = dealAllocationRule;
        this.lpAllocationRule = lpAllocationRule;
        this.customfields = customfields;
        this.needsToSetProgressiveValues = needsToSetProgressiveValues;
        this.isPartOfAllProgressiveRuns = isPartOfAllProgressiveRuns;
    }
}
