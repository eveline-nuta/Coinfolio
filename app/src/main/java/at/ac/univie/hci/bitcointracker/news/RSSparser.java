package at.ac.univie.hci.bitcointracker.news;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class RSSparser extends AsyncTask<Void,Void,Void> {

    Context context;
      String address = "https://www.coindesk.com/feed/";
    ProgressDialog progressDialog;
    ArrayList<TestArtikel> feedItems;
    RecyclerView recyclerView;
    URL url;

    public RSSparser(Context context, RecyclerView recyclerView) {
        this.recyclerView = recyclerView;
        this.context = context;
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Lädt...");
    }

    //ladeanzeige
    @Override
    protected void onPreExecute() {
        progressDialog.show();
        super.onPreExecute();
    }


    //methode bezieht rss feed
    @Override
    protected Void doInBackground(Void... params) {
        //ruft xml methode auf to process um doukument von getData() methode zu verarbeiten
        ProcessXml(Getdata());

        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        progressDialog.dismiss();
        FeedAdapter adapter = new FeedAdapter(context, feedItems);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.addItemDecoration(new Platzhalter(20));
        recyclerView.setAdapter(adapter);

    }

    //Methode bearbeitet erhaltenen RSS feed
    private void ProcessXml(Document data) {
        if (data != null) {
            feedItems = new ArrayList<>();
            Element root = data.getDocumentElement();
            Node channel = root.getChildNodes().item(1);
            NodeList items = channel.getChildNodes();
            for (int i = 0; i < items.getLength(); i++) {
                Node cureentchild = items.item(i);
                if (cureentchild.getNodeName().equalsIgnoreCase("item")) {
                    TestArtikel item = new TestArtikel();
                    NodeList itemchilds = cureentchild.getChildNodes();
                    for (int j = 0; j < itemchilds.getLength(); j++) {
                        Node current = itemchilds.item(j);
                        if (current.getNodeName().equalsIgnoreCase("title")) {
                            item.setTitle(current.getTextContent());
                        } else if (current.getNodeName().equalsIgnoreCase("description")) {
                            item.setDescription(current.getTextContent());
                        } else if (current.getNodeName().equalsIgnoreCase("Date")) {
                            item.setDate(current.getTextContent());
                        } else if (current.getNodeName().equalsIgnoreCase("link")) {
                            item.setLink(current.getTextContent());
                        } else if (current.getNodeName().equalsIgnoreCase("thumbnail")) {
                            //this will return us thumbnail url
                            String url = current.getAttributes().item(0).getTextContent();
                            item.setThumbnailUrl(url);
                        }
                    }
                    feedItems.add(item);


                }
            }
        }
    }

    //mthode lädt rss feed aus url
    public Document Getdata() {
        try {
            url = new URL(address);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            InputStream inputStream = connection.getInputStream();
            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = builderFactory.newDocumentBuilder();
            Document xmlDoc = builder.parse(inputStream);
            return xmlDoc;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
