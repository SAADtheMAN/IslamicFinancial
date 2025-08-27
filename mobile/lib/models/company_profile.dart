class CompanyProfile {
  final String symbol;
  final double? price;
  final double? beta;
  final int? volAvg;
  final int? mktCap;
  final String? companyName;
  final String? industry;
  final String? sector;

  CompanyProfile({
    required this.symbol,
    this.price,
    this.beta,
    this.volAvg,
    this.mktCap,
    this.companyName,
    this.industry,
    this.sector,
  });

  factory CompanyProfile.fromJson(Map<String, dynamic> json) {
    return CompanyProfile(
      symbol: json['symbol'] ?? '',
      price: (json['price'] as num?)?.toDouble(),
      beta: (json['beta'] as num?)?.toDouble(),
      volAvg: json['volAvg'] as int?,
      mktCap: json['mktCap'] as int?,
      companyName: json['companyName'],
      industry: json['industry'],
      sector: json['sector'],
    );
  }
}
