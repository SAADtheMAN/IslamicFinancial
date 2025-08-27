import 'package:flutter/material.dart';
import 'package:provider/provider.dart';
import '../providers/company_provider.dart';
import '../models/company.dart';
import 'company_details_screen.dart'; // Import the details screen

class HomeScreen extends StatefulWidget {
  const HomeScreen({super.key});

  @override
  State<HomeScreen> createState() => _HomeScreenState();
}

class _HomeScreenState extends State<HomeScreen> {
  @override
  void initState() {
    super.initState();
    // Fetch companies once the screen is loaded
    WidgetsBinding.instance.addPostFrameCallback((_) {
      _fetchCompanies();
    });
  }

  Future<void> _fetchCompanies() async {
    try {
      await Provider.of<CompanyProvider>(context, listen: false)
          .fetchCompanies();
    } catch (e) {
      ScaffoldMessenger.of(context).showSnackBar(
        SnackBar(content: Text('Failed to load companies: $e')),
      );
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text("Companies"),
        backgroundColor: Theme.of(context).colorScheme.inversePrimary,
      ),
      body: Consumer<CompanyProvider>(
        builder: (context, provider, child) {
          if (provider.loadingCompanies) {
            return const Center(child: CircularProgressIndicator());
          }

          if (provider.companies.isEmpty) {
            return RefreshIndicator(
              onRefresh: _fetchCompanies,
              child: const Center(child: Text("No companies found")),
            );
          }

          return RefreshIndicator(
            onRefresh: _fetchCompanies,
            child: ListView.builder(
              itemCount: provider.companies.length,
              itemBuilder: (context, index) {
                final Company company = provider.companies[index];
                return Card(
                  margin:
                  const EdgeInsets.symmetric(horizontal: 16, vertical: 8),
                  child: ListTile(
                    title: Text(company.name),
                    subtitle: company.sector != null ? Text(company.sector!) : null,
                    onTap: () {
                      Navigator.push(
                        context,
                        MaterialPageRoute(
                          builder: (_) => CompanyDetailsScreen(
                            companyId: company.id,
                            symbol: company.symbol,
                            name: company.name,
                          ),
                        ),
                      );
                    },
                  ),
                );
              },
            ),
          );
        },
      ),
    );
  }
}
