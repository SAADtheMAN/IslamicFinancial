class Company {
  final int id;
  final String name;
  final String sector;
  final String ticker;
  final double marketCap;

  Company({
    required this.id,
    required this.name,
    required this.sector,
    required this.ticker,
    required this.marketCap,
  });

  factory Company.fromJson(Map<String, dynamic> json) {
    return Company(
      id: json['id'] as int,
      name: json['name'] ?? '',
      sector: json['sector'] ?? '',
      ticker: json['ticker'] ?? '',
      marketCap: (json['marketCap'] is int)
          ? (json['marketCap'] as int).toDouble()
          : (json['marketCap'] as double? ?? 0.0),
    );
  }
}
