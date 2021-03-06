<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:output method="text" indent="no"/>
<xsl:strip-space elements="*"/>

<xsl:template match="S[count(S)=0][NP[count(CC) = 0 and (NNP|NNPS|PRP) and *[@nsubj]] and VP[(VB|VBZ|VBP)[@cop and @lemma='be'] and count(RB[@neg])=0 and ADJP/JJ]]">
DataBoolPropertyAssertion(
        <xsl:call-template name="adj_to_data_property">
            <xsl:with-param name="adj" select="VP/ADJP/JJ" />
        </xsl:call-template>
        <xsl:text> </xsl:text>
        <xsl:call-template name="noun_phrase_to_individual">
            <xsl:with-param name="NP" select="NP" />
        </xsl:call-template>
        <xsl:text> </xsl:text>
        true)
</xsl:template>

<xsl:template match="S[count(S)=0][NP[count(CC) = 0 and (NNP|NNPS|PRP) and *[@nsubj]] and VP[(VB|VBZ|VBP)[@cop and @lemma='be'] and count(RB[@neg])>0 and ADJP/JJ]]">
DataBoolPropertyAssertion(
        <xsl:call-template name="adj_to_data_property">
            <xsl:with-param name="adj" select="VP/ADJP/JJ" />
        </xsl:call-template>
        <xsl:text> </xsl:text>
        <xsl:call-template name="noun_phrase_to_individual">
            <xsl:with-param name="NP" select="NP" />
        </xsl:call-template>
        <xsl:text> </xsl:text>
        false)
</xsl:template>

</xsl:stylesheet>