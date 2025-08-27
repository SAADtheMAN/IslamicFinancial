import 'package:flutter/material.dart';
import 'package:provider/provider.dart';
import '../providers/company_provider.dart';
import '../models/company_profile.dart';
import '../models/financial_ratios.dart';
import '../models/shariah_compliance.dart';

class CompanyDetailsScreen extends StatefulWidget {
  final int companyId;
  final String symbol;
  final String name;

  const CompanyDetailsScreen({
    super.key,
    required this.companyId,
    required this.symbol,
    required this.name,
  });

  @override
  State<CompanyDetailsScreen> createState() => _CompanyDetailsScreenState();
}

class _CompanyDetailsScreenState extends State<CompanyDetailsScreen> {
  @override
  void initState() {
    super.initState();
    _fetchCompanyDetails();
  }

  Future<void> _fetchCompanyDetails() async {
    await Provider.of<CompanyProvider>(context, listen: false)
        .fetchCompanyDetails(widget.companyId, widget.symbol);
  }

  @override
  void dispose() {
    Provider.of<CompanyProvider>(context, listen: false).clearSelectedCompany();
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text(widget.name),
      ),
      body: Consumer<CompanyProvider>(
        builder: (context, provider, child) {
          if (provider.loadingDetails) {
            return const Center(child: CircularProgressIndicator());
          }

          if (provider.selectedProfile == null) {
            return const Center(child: Text("Failed to load company details"));
          }

          final CompanyProfile profile = provider.selectedProfile!;
          final FinancialRatios? ratios = provider.selectedRatios;
          final List<ShariahCompliance>? compliance = provider.selectedCompliance;

          return SingleChildScrollView(
            padding: const EdgeInsets.all(16),
            child: Column(
              crossAxisAlignment: CrossAxisAlignment.start,
              children: [
                // --- Profile ---
                Text(
                  "Profile",
                  style: Theme.of(context).textTheme.titleMedium,
                ),
                const SizedBox(height: 8),
                Text("Symbol: ${profile.symbol}"),
                Text("Industry: ${profile.industry}"),
                Text("Market Cap: ${profile.marketCap}"),
                const Divider(height: 32),

                // --- Financial Ratios ---
                if (ratios != null) ...[
                  Text(
                    "Financial Ratios",
                    style: Theme.of(context).textTheme.titleMedium,
                  ),
                  const SizedBox(height: 8),
                  Text("P/E: ${ratios.peRatio}"),
                  Text("EPS: ${ratios.eps}"),
                  Text("ROE: ${ratios.roe}"),
                  const Divider(height: 32),
                ],

                // --- Shariah Compliance ---
                if (compliance != null && compliance.isNotEmpty) ...[
                  Text(
                    "Shariah Compliance",
                    style: Theme.of(context).textTheme.titleMedium,
                  ),
                  const SizedBox(height: 8),
                  ...compliance.map(
                        (c) => ListTile(
                      contentPadding: EdgeInsets.zero,
                      title: Text(c.type),
                      subtitle: Text("Status: ${c.status}"),
                    ),
                  ),
                ],
              ],
            ),
          );
        },
      ),
    );
  }
}
