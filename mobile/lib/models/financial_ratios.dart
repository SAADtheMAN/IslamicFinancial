class FinancialRatios {
  final double? debtRatio;
  final double? liquidityRatio;
  final double? profitabilityRatio;

  FinancialRatios({
    this.debtRatio,
    this.liquidityRatio,
    this.profitabilityRatio,
  });

  factory FinancialRatios.fromJson(Map<String, dynamic> json) {
    return FinancialRatios(
      debtRatio: (json['debtRatio'] as num?)?.toDouble(),
      liquidityRatio: (json['liquidityRatio'] as num?)?.toDouble(),
      profitabilityRatio: (json['profitabilityRatio'] as num?)?.toDouble(),
    );
  }
}
